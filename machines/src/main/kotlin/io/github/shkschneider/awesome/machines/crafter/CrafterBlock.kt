package io.github.shkschneider.awesome.machines.crafter

import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.math.BlockPos

@Suppress("RemoveRedundantQualifierName")
class CrafterBlock(
    machine: AwesomeMachine<CrafterBlock.Entity, CrafterScreen.Handler>
) : AwesomeMachineBlock<CrafterBlock.Entity, CrafterScreen.Handler>(
    machine = machine,
    settings = FabricBlockSettings.copyOf(Blocks.FURNACE),
) {

    override fun tooltips(stack: ItemStack): List<Text> = listOf(
        Text.translatable(AwesomeUtils.translatable("block", machine.id, "hint")).formatted(Formatting.GRAY),
    )

    override fun blockEntity(machine: AwesomeMachine<CrafterBlock.Entity, CrafterScreen.Handler>, pos: BlockPos, state: BlockState): CrafterBlock.Entity =
        CrafterBlock.Entity(machine, pos, state)

    class Entity(
        machine: AwesomeMachine<CrafterBlock.Entity, CrafterScreen.Handler>,
        pos: BlockPos,
        state: BlockState,
    ) : AwesomeMachineBlockEntity<CrafterBlock.Entity, CrafterScreen.Handler>(
        machine, pos, state,
    ) {

        override fun screenHandler(syncId: Int, playerInventory: PlayerInventory, sidedInventory: SidedInventory, properties: PropertyDelegate): ScreenHandler =
            CrafterScreen.Handler(machine, machine.screen, syncId, playerInventory, sidedInventory, properties)

//        override fun canInsert(slot: Int, stack: ItemStack, dir: Direction?): Boolean {
//            dir ?: return false
//            return slot < Crafter.INVENTORY && dir != Direction.DOWN
//        }
//
//        override fun canExtract(slot: Int, stack: ItemStack, dir: Direction?): Boolean =
//            slot == Crafter.IO.size - 1 && dir == Direction.DOWN
//
//        override fun readNbt(nbt: NbtCompound) {
//            Inventories.readNbt(nbt, items)
//            super.readNbt(nbt)
//        }
//
//        override fun writeNbt(nbt: NbtCompound) {
//            super.writeNbt(nbt)
//            Inventories.writeNbt(nbt, items)
//        }

    }

}
