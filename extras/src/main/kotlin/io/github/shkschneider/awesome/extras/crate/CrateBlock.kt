package io.github.shkschneider.awesome.extras.crate

import io.github.shkschneider.awesome.core.AwesomeBlockWithEntity
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.ext.getStacks
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.ShapeContext
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtElement
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldView

class CrateBlock : AwesomeBlockWithEntity<CrateBlockEntity>(
    AwesomeUtils.identifier(Crate.ID), FabricBlockSettings.copy(Blocks.BARREL).strength(0.25F).nonOpaque(),
), AwesomeBlockWithEntity.RetainsInventory {

    override fun getRenderType(state: BlockState): BlockRenderType =
        BlockRenderType.MODEL

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape =
        createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean =
        world.getBlockState(pos).isAir && world.getBlockState(pos.down()).isSolidBlock(world, pos.down())

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean = false

    override fun createBlockEntity(pos: BlockPos, state: BlockState): CrateBlockEntity =
        CrateBlockEntity(pos, state)

    override fun appendTooltip(stack: ItemStack, world: BlockView?, tooltip: MutableList<Text>, options: TooltipContext) {
        super.appendTooltip(stack, world, tooltip, options)
        BlockItem.getBlockEntityNbt(stack)?.let { nbt ->
            if (nbt.contains("Items", NbtElement.LIST_TYPE.toInt())) {
                SimpleInventory(Crate.IO.size).apply {
                    DefaultedList.ofSize(Crate.IO.size, ItemStack.EMPTY).apply {
                        Inventories.readNbt(nbt, this)
                    }.forEach(this::addStack)
                }.getStacks().filterNot { it.isEmpty }.forEach { itemStack ->
                    tooltip.add(itemStack.name.copy().append(" x").append(itemStack.count.toString()))
                }
            }
        }
    }

    override fun hasComparatorOutput(state: BlockState): Boolean = true

    override fun getComparatorOutput(state: BlockState, world: World, pos: BlockPos): Int =
        ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos))

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: CrateBlockEntity) {}

}
