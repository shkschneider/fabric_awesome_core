package io.github.shkschneider.awesome.core

import io.github.shkschneider.awesome.custom.ImplementedInventory
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos

abstract class AwesomeBlockEntity(
    type: BlockEntityType<out BlockEntity>,
    pos: BlockPos,
    state: BlockState,
) : BlockEntity(type, pos, state) {

    @Suppress("RedundantOverride")
    abstract class WithInventory(
        type: BlockEntityType<out BlockEntity>,
        pos: BlockPos,
        state: BlockState,
    ) : AwesomeBlockEntity(type, pos, state), ImplementedInventory {

        abstract override val items: DefaultedList<ItemStack>

        override fun getStack(slot: Int): ItemStack =
            super.getStack(slot)

        override fun setStack(slot: Int, stack: ItemStack) =
            super.setStack(slot, stack)

        override fun clear() =
            super.clear()

        override fun canPlayerUse(player: PlayerEntity): Boolean =
            true

        override fun markDirty() {
            super.markDirty()
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
