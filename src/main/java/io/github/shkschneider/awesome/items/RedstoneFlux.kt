package io.github.shkschneider.awesome.items

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.core.AwesomeItem
import io.github.shkschneider.awesome.core.Minecraft
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.Rarity

class RedstoneFlux : AwesomeItem(
    id = AwesomeUtils.identifier("redstone_flux"),
    settings = FabricItemSettings().maxCount(Minecraft.STACK).group(Awesome.GROUP).rarity(Rarity.UNCOMMON),
) {

    val time = FuelRegistry.INSTANCE.get(Items.COAL) * 2

    override fun hasGlint(stack: ItemStack): Boolean = true

}
