package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.core.AwesomeBlockWithEntity
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.minecraft.block.Block
import net.minecraft.block.BlockState
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
import net.minecraft.world.World

class GeneratorBlock(
    settings: Settings,
) : AwesomeBlockWithEntity<GeneratorBlockEntity>(AwesomeUtils.identifier(Generator.ID), settings) {

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState =
        defaultState.with(Properties.HORIZONTAL_FACING, ctx.playerFacing.opposite).with(Properties.LIT, false)

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState =
        state.with(Properties.HORIZONTAL_FACING, rotation.rotate(state.get(Properties.HORIZONTAL_FACING)))

    override fun mirror(state: BlockState, mirror: BlockMirror): BlockState =
        state.rotate(mirror.getRotation(state.get(Properties.HORIZONTAL_FACING)))

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.HORIZONTAL_FACING).add(Properties.LIT)
    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: GeneratorBlockEntity) {
        Generator.tick(world, pos, state, blockEntity)
    }

    override fun emitsRedstonePower(state: BlockState): Boolean = state.get(Properties.LIT)

    override fun createBlockEntity(pos: BlockPos, state: BlockState): GeneratorBlockEntity =
        GeneratorBlockEntity(pos, state)

    override fun onStateReplaced(state: BlockState, world: World, pos: BlockPos, newState: BlockState, moved: Boolean) {
        if (state.block != newState.block) {
            (world.getBlockEntity(pos) as? Inventory)?.let { ItemScatterer.spawn(world, pos, it) }
            world.updateComparators(pos, this)
            @Suppress("DEPRECATION")
            super.onStateReplaced(state, world, pos, newState, moved)
        }
    }

    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hit: BlockHitResult): ActionResult {
        if (!world.isClient) {
            state.createScreenHandlerFactory(world, pos)?.let(player::openHandledScreen)
        }
        return ActionResult.SUCCESS
    }

}
