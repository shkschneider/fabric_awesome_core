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
