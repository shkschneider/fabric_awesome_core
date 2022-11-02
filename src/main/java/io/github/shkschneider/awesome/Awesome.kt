package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.commands.AwesomeCommands
import io.github.shkschneider.awesome.custom.SilkTouchSpawners
import io.github.shkschneider.awesome.effects.AwesomeEffects
import io.github.shkschneider.awesome.enchantments.AwesomeEnchantments
import io.github.shkschneider.awesome.gamerules.AwesomeGameRules
import io.github.shkschneider.awesome.machines.AwesomeMachines
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import io.github.shkschneider.awesome.worldgen.AwesomeWorldGen
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

class Awesome : ModInitializer {

    companion object {

        val ID = Awesome::class.java.simpleName.lowercase()
        val NAME = ID.replaceFirstChar { it.uppercase() }

        val GROUP = FabricItemGroupBuilder.build(Identifier(ID, ID)) { ItemStack(AwesomeMaterials.redstoneFlux) }

    }

    override fun onInitialize() {
        println("$NAME!")
        AwesomeCommands()
        // AwesomeDimensions()
        AwesomeEffects()
        AwesomeEnchantments()
        AwesomeGameRules()
        AwesomeMachines()
        AwesomeMaterials()
        AwesomeWorldGen()
        if (AwesomeConfig.silkTouchSpawners) SilkTouchSpawners()
    }

}
