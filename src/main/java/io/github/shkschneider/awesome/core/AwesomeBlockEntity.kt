package io.github.shkschneider.awesome.core

import io.github.shkschneider.awesome.core.ext.readNbt
import io.github.shkschneider.awesome.core.ext.writeNbt
import io.github.shkschneider.awesome.custom.Faces
import io.github.shkschneider.awesome.custom.Faces.Companion.relativeFace
import io.github.shkschneider.awesome.custom.IInventory
import io.github.shkschneider.awesome.custom.InputOutput
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.PropertyDelegate
import net.minecraft.state.property.Property
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

abstract class AwesomeBlockEntity(
    private val id: String,
    type: BlockEntityType<out AwesomeBlockEntity>,
    pos: BlockPos,
    private val state: BlockState,
    private val slots: InputOutput.Slots,
    delegates: Pair<Int, Int>,
) : BlockEntity(type, pos, state) {

    private val _properties: MutableMap<Int, Int> = buildMap {
        (0 until delegates.first).forEach { i ->
            put(i, delegates.second)
        }
    }.toMutableMap()

    protected val properties = object : PropertyDelegate {
        override fun get(index: Int): Int = _properties.getValue(index)
        override fun set(index: Int, value: Int) { _properties[index] = value }
        override fun size(): Int = _properties.size
    }

    fun <T : Comparable<T>> getPropertyState(property: Property<T>): T {
        if (state.properties.none { it == property }) throw IllegalArgumentException()
        return world?.getBlockState(pos)?.get(property) ?: state.get(property)
    }

    fun <T : Comparable<T>, V : T> setPropertyState(property: Property<T>, value: V) {
        if (state.properties.none { it == property }) throw IllegalArgumentException()
        world?.setBlockState(pos, state.with(property, value))
        this@AwesomeBlockEntity.markDirty()
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        properties.writeNbt(nbt)
    }

    override fun readNbt(nbt: NbtCompound) {
        properties.readNbt(nbt)
        super.readNbt(nbt)
    }

    abstract class WithInventory(
        id: String,
        type: BlockEntityType<out AwesomeBlockEntity>,
        pos: BlockPos,
        private val state: BlockState,
        private val slots: InputOutput.Slots,
        delegates: Pair<Int, Int>,
    ) : AwesomeBlockEntity(id, type, pos, state, slots, delegates), IInventory, SidedInventory {

        override val items: DefaultedList<ItemStack> = DefaultedList.ofSize(slots.size, ItemStack.EMPTY)

        override fun getAvailableSlots(side: Direction?): IntArray =
            (0 until slots.size).toList().toIntArray()

        override fun canInsert(slot: Int, stack: ItemStack, dir: Direction?): Boolean {
            dir ?: return false
            val face = dir.relativeFace(state)
            return slots.isOutput(slot).not() && (face == Faces.Top || face is Faces.Side)
        }

        override fun canExtract(slot: Int, stack: ItemStack, dir: Direction): Boolean {
            val face = dir.relativeFace(state)
            return slots.isOutput(slot) && face == Faces.Bottom
        }

        override fun writeNbt(nbt: NbtCompound) {
            super.writeNbt(nbt)
            Inventories.writeNbt(nbt, items)
        }

        override fun readNbt(nbt: NbtCompound) {
            Inventories.readNbt(nbt, items)
            super.readNbt(nbt)
        }

    }

}
