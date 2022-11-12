package io.github.shkschneider.awesome.core

import io.github.shkschneider.awesome.custom.IInventory
import io.github.shkschneider.awesome.custom.InputOutput
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

abstract class AwesomeBlockEntity(
    type: BlockEntityType<out BlockEntity>,
    pos: BlockPos,
    state: BlockState,
    // TODO properties
) : BlockEntity(type, pos, state) {

    abstract val properties: Map<Int, Int>

    val delegate = object : PropertyDelegate {
        override fun get(index: Int): Int = properties.getValue(index)
        override fun set(index: Int, value: Int) { properties.toMutableMap()[index] = value }
        override fun size(): Int = properties.size
    }

    abstract class WithInventory(
        type: BlockEntityType<out BlockEntity>,
        pos: BlockPos,
        val state: BlockState,
        val slots: InputOutput.Slots,
    ) : AwesomeBlockEntity(type, pos, state), IInventory, SidedInventory {

        override val items: DefaultedList<ItemStack> = DefaultedList.ofSize(slots.size, ItemStack.EMPTY)

        override fun canPlayerUse(player: PlayerEntity): Boolean = true

        override fun writeNbt(nbt: NbtCompound) {
            super.writeNbt(nbt)
            // TODO properties
            Inventories.writeNbt(nbt, items)
        }

        override fun readNbt(nbt: NbtCompound) {
            Inventories.readNbt(nbt, items)
            // TODO properties
            super.readNbt(nbt)
        }

        override fun getAvailableSlots(side: Direction): IntArray =
            (0 until slots.size).toList().toIntArray()

        override fun canInsert(slot: Int, stack: ItemStack?, dir: Direction?): Boolean = true

        override fun canExtract(slot: Int, stack: ItemStack?, dir: Direction?): Boolean = true

    }

    abstract class WithScreen(
        type: BlockEntityType<out BlockEntity>,
        pos: BlockPos,
        state: BlockState,
        slots: InputOutput.Slots,
    ) : WithInventory(type, pos, state, slots), NamedScreenHandlerFactory {

        abstract override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity?): AwesomeBlockScreen.Handler

    }

}
