package io.github.shkschneider.awesome.custom

import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack

object InputOutput {

    class Slots(
        val inputs: Int,
        val fuel: ItemStack? = ItemStack(AwesomeMaterials.redstoneFlux, 1),
        val outputs: Int = 1,
    ) {

        val size: Int = inputs + (if (fuel != null) 1 else 0) + outputs

        fun isInput(slot: Int) = slot in (0 until inputs)

        fun isFuel(slot: Int) = fuel != null && slot == inputs

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
