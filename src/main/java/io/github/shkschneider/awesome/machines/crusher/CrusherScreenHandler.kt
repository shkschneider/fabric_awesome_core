package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.custom.addPlayerHotbar
import io.github.shkschneider.awesome.custom.addPlayerInventory
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot

class CrusherScreenHandler(
    syncId: Int,
    playerInventory: PlayerInventory,
    private val inventory: Inventory,
    private val properties: PropertyDelegate
) : ScreenHandler(Crusher.SCREEN, syncId) {

    val outputProgress: Int get() = properties[Crusher.Properties.OutputProgress.ordinal]

    init {
        inventory.onOpen(playerInventory.player)
        addSlot(Slot(inventory, 0, 76, 47))
        addSlot(Slot(inventory, 1, 134, 47))
        addPlayerInventory(playerInventory, ::addSlot)
        addPlayerHotbar(playerInventory, ::addSlot)
        addProperties(properties)
    }

    override fun transferSlot(player: PlayerEntity, slot: Int): ItemStack {
        if (slot < Crusher.SLOTS.first + Crusher.SLOTS.second) {
            player.inventory.offerOrDrop(slots[slot].stack)
            return slots[slot].stack
        } else {
            (0 until Crusher.SLOTS.first).forEach { i ->
                if (CrusherRecipes().any { recipe -> recipe.inputs.any { it.item == slots[slot].stack.item } }) {
                    // TODO
                }
            }
            return ItemStack.EMPTY // FIXME
        }
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return inventory.canPlayerUse(player)
    }

}