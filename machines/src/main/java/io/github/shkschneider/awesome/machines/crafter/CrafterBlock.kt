package io.github.shkschneider.awesome.machines.crafter

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.AwesomeMachines
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
        pos, state, Crafter.PORTS, emptyList(),
        screenHandlerProvider = { syncId, sidedInventory, playerInventory, properties ->
            CrafterScreen.Handler(syncId, sidedInventory, playerInventory, properties)
        },
    ) {

        override fun canInsert(slot: Int, stack: ItemStack, dir: Direction?): Boolean {
            dir ?: return false
            return slot < Crafter.INVENTORY && dir != Direction.DOWN
        }

        override fun canExtract(slot: Int, stack: ItemStack, dir: Direction): Boolean =
            slot == Crafter.PORTS.size - 1 && dir == Direction.DOWN

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

