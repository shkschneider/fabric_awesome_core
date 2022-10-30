package io.github.shkschneider.awesome.machines.collector

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot

class CollectorScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
    private val inventory: Inventory,
) : ScreenHandler(Collector.SCREEN, syncId) {

    init {
        checkSize(inventory, Collector.IO)
        inventory.onOpen(playerInventory.player)
        addSlot(Slot(inventory, 0, 62, 17))
        addSlot(Slot(inventory, 1, 80, 17))
        addSlot(Slot(inventory, 2, 98, 17))
        addSlot(Slot(inventory, 3, 62, 35))
        addSlot(Slot(inventory, 4, 80, 35))
        addSlot(Slot(inventory, 5, 98, 35))
        addSlot(Slot(inventory, 6, 62, 53))
        addSlot(Slot(inventory, 7, 80, 53))
        addSlot(Slot(inventory, 8, 98, 53))
        addPlayerSlots(playerInventory)
    }

    override fun transferSlot(player: PlayerEntity, invSlot: Int): ItemStack {
        if (invSlot < Collector.IO) {
            // from entity
            player.inventory.offerOrDrop(slots[invSlot].stack)
            return ItemStack.EMPTY
        }
        return ItemStack.EMPTY
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return inventory.canPlayerUse(player)
    }

    private fun addPlayerSlots(playerInventory: PlayerInventory) {
        // inventory
        for (i in 0..2) {
            for (l in 0..8) {
                addSlot(Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18))
            }
        }
        // hotbar
        for (i in 0..8) {
            addSlot(Slot(playerInventory, i, 8 + i * 18, 142))
        }
    }

}