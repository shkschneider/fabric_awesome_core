package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.commands.AwesomeCommands
import io.github.shkschneider.awesome.effects.AwesomeEffects
import io.github.shkschneider.awesome.enchantments.AwesomeEnchantments
import io.github.shkschneider.awesome.events.AwesomeEvents
import io.github.shkschneider.awesome.gamerules.AwesomeGameRules
import io.github.shkschneider.awesome.loots.AwesomeLoots
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

class Awesome : ModInitializer {

    companion object {

        const val ID = "awesome"

        val GROUP = FabricItemGroupBuilder.build(Identifier(ID, ID)) { ItemStack(AwesomeMaterials.redstoneFlux) }

    }

    override fun onInitialize() {
        Logger.debug("Awesome!")
        AwesomeCommands()
        AwesomeEffects()
        AwesomeEnchantments()
        AwesomeEvents()
        AwesomeGameRules()
        AwesomeLoots()
        AwesomeMaterials()
    }

}
