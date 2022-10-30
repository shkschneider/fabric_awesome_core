package io.github.shkschneider.awesome.machines.collector

import io.github.shkschneider.awesome.custom.addPlayerHotbar
import io.github.shkschneider.awesome.custom.addPlayerInventory
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
        addPlayerInventory(playerInventory, ::addSlot)
        addPlayerHotbar(playerInventory, ::addSlot)
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

}