package io.github.shkschneider.awesome.machines.quarry

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeBlockWithEntity
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.BlockMirror
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class QuarryBlock : AwesomeBlockWithEntity.WithInventory<QuarryBlockEntity>(
    id = AwesomeUtils.identifier(Quarry.ID),
    settings = FabricBlockSettings.copyOf(Blocks.FURNACE),
    group = Awesome.GROUP,
) {

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState = defaultState
        .with(Properties.HORIZONTAL_FACING, ctx.playerFacing.opposite)
        .with(Properties.LIT, false)

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState =
        state.with(Properties.HORIZONTAL_FACING, rotation.rotate(state.get(Properties.HORIZONTAL_FACING)))

    override fun mirror(state: BlockState, mirror: BlockMirror): BlockState =
        state.rotate(mirror.getRotation(state.get(Properties.HORIZONTAL_FACING)))

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder
            .add(Properties.HORIZONTAL_FACING)
            .add(Properties.LIT)
    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: QuarryBlockEntity) {
        if (world.isClient) return
        Quarry.tick(world, pos, state, blockEntity)
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): QuarryBlockEntity =
        QuarryBlockEntity(pos, state)

}
