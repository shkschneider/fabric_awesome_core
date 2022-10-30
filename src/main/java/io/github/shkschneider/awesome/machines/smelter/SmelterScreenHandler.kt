package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.custom.addPlayerHotbar
import io.github.shkschneider.awesome.custom.addPlayerInventory
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot

class SmelterScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
    private val inventory: Inventory,
    private val properties: PropertyDelegate
) : ScreenHandler(Smelter.SCREEN, syncId) {

    val inputProgress: Int get() = properties[Smelter.Properties.InputProgress.ordinal]
    val outputProgress: Int get() = properties[Smelter.Properties.OutputProgress.ordinal]

    init {
        inventory.onOpen(playerInventory.player)
        addSlot(Slot(inventory, 0, 56, 17))
        addSlot(Slot(inventory, 1, 56, 53))
        addSlot(Slot(inventory, 2, 112 + 4, 31 + 4))
        addPlayerInventory(playerInventory, ::addSlot)
        addPlayerHotbar(playerInventory, ::addSlot)
        addProperties(properties)
    }

    override fun transferSlot(player: PlayerEntity, slot: Int): ItemStack {
        if (slot < Smelter.SLOTS.first + Smelter.SLOTS.second) {
            player.inventory.offerOrDrop(slots[slot].stack)
            return slots[slot].stack
        } else {
            // TODO
            return ItemStack.EMPTY
        }
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return inventory.canPlayerUse(player)
    }

}