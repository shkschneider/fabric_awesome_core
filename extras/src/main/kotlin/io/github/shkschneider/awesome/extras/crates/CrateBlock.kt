package io.github.shkschneider.awesome.extras.crates

import io.github.shkschneider.awesome.core.AwesomeBlockEntity
import io.github.shkschneider.awesome.core.AwesomeBlockWithEntity
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.ext.getStacks
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.ShapeContext
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtElement
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World

@Suppress("RemoveRedundantQualifierName")
class CrateBlock : AwesomeBlockWithEntity<CrateBlock.Entity>(
    AwesomeUtils.identifier(Crates.ID), FabricBlockSettings.copy(Blocks.BARREL).nonOpaque(),
), AwesomeBlockWithEntity.RetainsInventory {

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape =
        createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0)

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean = false

    override fun createBlockEntity(pos: BlockPos, state: BlockState): CrateBlock.Entity =
        CrateBlock.Entity(pos, state)

    override fun appendTooltip(stack: ItemStack, world: BlockView?, tooltip: MutableList<Text>, options: TooltipContext) {
        super.appendTooltip(stack, world, tooltip, options)
        BlockItem.getBlockEntityNbt(stack)?.let { nbt ->
            if (nbt.contains("Items", NbtElement.LIST_TYPE.toInt())) {
                SimpleInventory(Crates.IO.size).apply {
                    DefaultedList.ofSize(Crates.IO.size, ItemStack.EMPTY).apply {
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

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: CrateBlock.Entity) = Unit

    class Entity(
        pos: BlockPos, state: BlockState,
    ) : AwesomeBlockEntity.WithInventory(
        Crates.ID, Crates.block.entityType, pos, state, Crates.IO, 0 to 0,
    ), AwesomeBlockEntity.WithScreen {

        override fun getDisplayName(): Text =
            TranslatableText(AwesomeUtils.translatable("block", Crates.ID))

        override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ScreenHandler =
            CrateBlockScreen.Handler(Crates.screen, syncId, playerInventory, this)

        override fun canInsert(slot: Int, stack: ItemStack, dir: Direction?): Boolean = true

        override fun canExtract(slot: Int, stack: ItemStack, dir: Direction?): Boolean = true

    }

}
