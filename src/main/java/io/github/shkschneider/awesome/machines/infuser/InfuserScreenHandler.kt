package io.github.shkschneider.awesome.machines.infuser

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
        checkSize(inventory, Infuser.IO.values().size)
        inventory.onOpen(playerInventory.player)
        addSlot(Slot(inventory, Infuser.IO.InputLeft1.ordinal, 27, 47))
        addSlot(Slot(inventory, Infuser.IO.InputLeft2.ordinal, 76, 47))
        addSlot(Slot(inventory, Infuser.IO.OutputRight.ordinal, 134, 47))
        addPlayerSlots(playerInventory)
        addProperties(properties)
    }

    override fun transferSlot(player: PlayerEntity, invSlot: Int): ItemStack {
        if (invSlot < Infuser.IO.values().size) {
            // from entity
            player.inventory.offerOrDrop(slots[invSlot].stack)
            return slots[invSlot].stack
        } else {
            // from player
            listOf(Infuser.IO.InputLeft1, Infuser.IO.InputLeft2).forEach { io ->
                if (slots[invSlot].stack.item == io.items.item) {
                    if (inventory.getStack(io.ordinal).isEmpty) {
                        inventory.setStack(io.ordinal, slots[invSlot].stack)
                        inventory.markDirty()
                        slots[invSlot].stack = ItemStack.EMPTY
                        slots[invSlot].markDirty()
                    } else {
                        inventory.getStack(io.ordinal).count = inventory.getStack(io.ordinal).maxCount
                        inventory.markDirty()
                        slots[invSlot].stack = ItemStack.EMPTY
                        slots[invSlot].markDirty()
                    }
                }
            }
            return ItemStack.EMPTY
        }
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