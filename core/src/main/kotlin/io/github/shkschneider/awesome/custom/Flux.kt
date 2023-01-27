package io.github.shkschneider.awesome.custom

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeItem
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.Rarity

class Flux : AwesomeItem(
    id = AwesomeUtils.identifier("flux"),
    settings = FabricItemSettings().maxCount(Minecraft.STACK).rarity(Rarity.UNCOMMON),
    group = Awesome.GROUP,
) {

    val time = FuelRegistry.INSTANCE.get(Items.COAL) * 2 // ~3200t

    override fun hasGlint(stack: ItemStack): Boolean = false

}
