package io.github.shkschneider.awesome.custom

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory

object InputOutput {

    class Slots(
        val inputs: Int,
        val outputs: Int,
    ) {

        val size: Int = inputs + outputs

        fun isInput(slot: Int) = slot in (0 until inputs)

        fun isOutput(slot: Int) = slot in (inputs until size)

    }

    class Inventories(
        val internal: Inventory,
        val player: PlayerInventory,
    ) {

        init {
            check(internal !is PlayerInventory)
        }

    }
}
