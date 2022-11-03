package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.custom.InputOutput
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot

abstract class AwesomeMachineScreenHandler(
    screen: ScreenHandlerType<*>,
    syncId: Int,
    private val inventories: InputOutput.Inventories,
    private val properties: PropertyDelegate,
) : ScreenHandler(screen, syncId) {

    val inputProgress: Int get() = properties[0]
    val outputProgress: Int get() = properties[1]

    init {
        // addProperties(properties)
        inventories.internal.onOpen(inventories.player.player)
        // addSlot(...)
        // addPlayerSlots()
    }

    // https://fabricmc.net/wiki/tutorial:containers
    fun addPlayerSlots() {
        // inventory
        for (i in 0..2) {
            for (l in 0..8) {
                addSlot(Slot(inventories.player, l + i * 9 + 9, 8 + l * 18, 84 + i * 18))
            }
        }
        // hotbar
        for (i in 0..8) {
            addSlot(Slot(inventories.player, i, 8 + i * 18, 142))
        }
    }

    // https://fabricmc.net/wiki/tutorial:containers
    // FIXME do not insert anything anywhere
    override fun transferSlot(player: PlayerEntity, i: Int): ItemStack {
        var stack = ItemStack.EMPTY
        slots.getOrNull(i)?.takeIf { it.hasStack() }?.let { slot ->
            stack = slot.stack.copy()
            if (i < inventories.internal.size()) {
                if (!insertItem(slot.stack, inventories.internal.size(), slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!insertItem(slot.stack, 0, inventories.internal.size(), false)) {
                return ItemStack.EMPTY
            }
            if (slot.stack.isEmpty) slot.stack = ItemStack.EMPTY
            else slot.markDirty()
        }
        return stack
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return inventories.internal.canPlayerUse(player)
    }

}
