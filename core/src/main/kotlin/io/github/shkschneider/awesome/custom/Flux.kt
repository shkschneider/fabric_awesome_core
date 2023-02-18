package io.github.shkschneider.awesome.custom

import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.Rarity

class Flux : Item(
    FabricItemSettings().maxCount(Minecraft.STACK).rarity(Rarity.UNCOMMON),
) {

    init {
        AwesomeRegistries.item(AwesomeUtils.identifier("flux"), this, null)
        FuelRegistry.INSTANCE.get(Items.COAL_BLOCK)
    }

    // AbstractFurnaceBlockEntity.createFuelTimeMap()
    val time: Int = FuelRegistry.INSTANCE.get(Items.BLAZE_ROD)

    override fun hasGlint(stack: ItemStack): Boolean = false

}
