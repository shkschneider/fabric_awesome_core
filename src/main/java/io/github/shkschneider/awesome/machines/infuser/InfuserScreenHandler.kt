package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.custom.addPlayerHotbar
import io.github.shkschneider.awesome.custom.addPlayerInventory
import io.github.shkschneider.awesome.machines.crusher.Crusher
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot

class InfuserScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
    private val inventory: Inventory,
    private val properties: PropertyDelegate
) : ScreenHandler(Infuser.SCREEN, syncId) {

    val outputProgress: Int get() = properties[Infuser.Properties.OutputProgress.ordinal]

    init {
        inventory.onOpen(playerInventory.player)
        addSlot(Slot(inventory, 0, 27, 47))
        addSlot(Slot(inventory, 1, 76, 47))
        addSlot(Slot(inventory, 2, 134, 47))
        addPlayerInventory(playerInventory, ::addSlot)
        addPlayerHotbar(playerInventory, ::addSlot)
        addProperties(properties)
    }

    override fun transferSlot(player: PlayerEntity, slot: Int): ItemStack {
        if (slot < Crusher.SLOTS.first + Crusher.SLOTS.second) {
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