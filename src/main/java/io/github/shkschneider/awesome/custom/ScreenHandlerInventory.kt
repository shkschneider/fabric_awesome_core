package io.github.shkschneider.awesome.custom

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot

fun ScreenHandler.addPlayerInventory(playerInventory: PlayerInventory, addSlot: (Slot) -> Unit) {
    for (i in 0..2) {
        for (l in 0..8) {
            addSlot(Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18))
        }
    }
}

fun ScreenHandler.addPlayerHotbar(playerInventory: PlayerInventory, addSlot: (Slot) -> Unit) {
    for (i in 0..8) {
        addSlot(Slot(playerInventory, i, 8 + i * 18, 142))
    }
}