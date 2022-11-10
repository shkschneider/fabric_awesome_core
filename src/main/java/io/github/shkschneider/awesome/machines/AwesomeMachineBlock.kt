package io.github.shkschneider.awesome.machines

import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.minecraft.util.BlockMirror
import net.minecraft.util.BlockRotation
import net.minecraft.util.Hand
import net.minecraft.util.ItemScatterer
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView
import net.minecraft.world.World

abstract class AwesomeMachineBlock<BE : BlockEntity>(
    settings: Settings,
    private val entityTypeProvider: () -> BlockEntityType<BE>,
    private val blockEntityProvider: (BlockPos, BlockState) -> BE,
    private val tickerProvider: () -> BlockEntityTicker<BE>,
) : BlockWithEntity(settings), BlockEntityProvider {

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.MODEL
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        return defaultState.with(Properties.HORIZONTAL_FACING, ctx.playerFacing.opposite)
            .with(Properties.LIT, false)
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(Properties.HORIZONTAL_FACING, rotation.rotate(state.get(Properties.HORIZONTAL_FACING)))
    }

    override fun mirror(state: BlockState, mirror: BlockMirror): BlockState {
        return state.rotate(mirror.getRotation(state.get(Properties.HORIZONTAL_FACING)))
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.HORIZONTAL_FACING).add(Properties.LIT)
    }

    override fun emitsRedstonePower(state: BlockState): Boolean = false

    override fun getStrongRedstonePower(state: BlockState, world: BlockView, pos: BlockPos, direction: Direction): Int =
        if (emitsRedstonePower(state)) 16 else 0

    override fun getWeakRedstonePower(state: BlockState, world: BlockView, pos: BlockPos, direction: Direction): Int =
        if (emitsRedstonePower(state)) 8 else 0

    override fun onStateReplaced(state: BlockState, world: World, pos: BlockPos, newState: BlockState, moved: Boolean) {
        if (state.block !== newState.block) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity is Inventory) {
                ItemScatterer.spawn(world, pos, blockEntity)
            }
            world.updateComparators(pos, this)
            super.onStateReplaced(state, world, pos, newState, moved)
        }
    }

    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hit: BlockHitResult): ActionResult {
        if (world.isClient) return super.onUse(state, world, pos, player, hand, hit)
        val screenHandlerFactory = state.createScreenHandlerFactory(world, pos)
        if (screenHandlerFactory != null) {
            player.openHandledScreen(screenHandlerFactory)
        }
        return ActionResult.SUCCESS
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BE {
        return blockEntityProvider(pos, state)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : BlockEntity> getTicker(_world: World, _state: BlockState, type: BlockEntityType<T>): BlockEntityTicker<T>? {
        return checkType(type, type) { world, pos, state, entity ->
            tickerProvider().tick(world, pos, state, entity as BE)
        }
    }

}
