package io.github.shkschneider.awesome.machines.smelter

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
        checkSize(inventory, Smelter.IO.values().size)
        inventory.onOpen(playerInventory.player)
        addSlot(Slot(inventory, Smelter.IO.InputTop.ordinal, 56, 17))
        addSlot(Slot(inventory, Smelter.IO.InputBottom.ordinal, 56, 53))
        addSlot(Slot(inventory, Smelter.IO.OutputRight.ordinal, 112 + 4, 31 + 4))
        addPlayerSlots(playerInventory)
        addProperties(properties)
    }

    override fun transferSlot(player: PlayerEntity, invSlot: Int): ItemStack {
        if (invSlot < Smelter.IO.values().size) {
            // from entity
            player.inventory.offerOrDrop(slots[invSlot].stack)
            return slots[invSlot].stack
        } else {
            // from player
            listOf(Smelter.IO.InputTop, Smelter.IO.InputBottom).forEach { io ->
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