package io.github.shkschneider.awesome.machines.crafter

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import net.minecraft.block.BlockState
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

class CrafterBlock(settings: Settings) : AwesomeMachineBlock<CrafterBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.crafter.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.crafter },
) {

    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Crafter.ID, AwesomeMachines.crafter.entityType,
        pos, state, Crafter.IO, emptyList(),
        screenHandlerProvider = { syncId, blockEntity, playerInventory, properties ->
            CrafterScreen.Handler(syncId, blockEntity, playerInventory, properties)
        },
    ) {

        override fun canInsert(slot: Int, stack: ItemStack, dir: Direction?): Boolean {
            dir ?: return false
            return slot < Crafter.INVENTORY && dir != Direction.DOWN
        }

        override fun canExtract(slot: Int, stack: ItemStack, dir: Direction?): Boolean =
            slot == Crafter.IO.size - 1 && dir == Direction.DOWN

        override fun readNbt(nbt: NbtCompound) {
            Inventories.readNbt(nbt, items)
            super.readNbt(nbt)
        }

        override fun writeNbt(nbt: NbtCompound) {
            super.writeNbt(nbt)
            Inventories.writeNbt(nbt, items)
        }

    }

}

