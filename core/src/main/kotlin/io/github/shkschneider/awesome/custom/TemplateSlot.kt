package io.github.shkschneider.awesome.custom

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.screen.slot.Slot

class TemplateSlot(inventory: Inventory, index: Int, x: Int, y: Int) : Slot(inventory, index, x, y) {

    override fun isEnabled(): Boolean = true

    override fun canTakeItems(playerEntity: PlayerEntity): Boolean = false

    override fun canTakePartial(player: PlayerEntity): Boolean = false

}
