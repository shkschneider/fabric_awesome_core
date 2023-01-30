package io.github.shkschneider.awesome.custom

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeItem
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.Rarity

class Flux : AwesomeItem(
    id = AwesomeUtils.identifier("flux"),
    settings = FabricItemSettings().maxCount(Minecraft.STACK).rarity(Rarity.UNCOMMON),
    group = Awesome.GROUP,
) {

    val time: Int = FuelRegistry.INSTANCE.get(Blocks.COAL_BLOCK)

    override fun hasGlint(stack: ItemStack): Boolean = false

}
