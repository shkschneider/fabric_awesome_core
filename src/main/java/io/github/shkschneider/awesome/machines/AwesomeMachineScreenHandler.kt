package io.github.shkschneider.awesome.machines

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot

abstract class AwesomeMachineScreenHandler(
    screen: ScreenHandlerType<*>,
    syncId: Int,
    private val playerInventory: PlayerInventory,
    val inventory: Inventory,
    val properties: PropertyDelegate,
) : ScreenHandler(screen, syncId) {

    val inputProgress: Int get() = properties[0]
    val outputProgress: Int get() = properties[1]

    init {
        // addProperties(properties)
        inventory.onOpen(playerInventory.player)
        // addSlot(...)
        // addPlayerSlots()
    }

    fun addPlayerSlots() {
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

    override fun transferSlot(player: PlayerEntity, slot: Int): ItemStack {
        return player.inventory.getStack(slot)
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return inventory.canPlayerUse(player)
    }

}