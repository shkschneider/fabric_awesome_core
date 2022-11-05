package io.github.shkschneider.awesome

import com.google.gson.annotations.SerializedName
import io.github.shkschneider.awesome.commands.AwesomeCommands
import io.github.shkschneider.awesome.custom.AwesomeConfig
import io.github.shkschneider.awesome.custom.SilkTouchSpawners
import io.github.shkschneider.awesome.effects.AwesomeEffects
import io.github.shkschneider.awesome.enchantments.AwesomeEnchantments
import io.github.shkschneider.awesome.gamerules.AwesomeGameRules
import io.github.shkschneider.awesome.items.AwesomeItems
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

        val CONFIG = AwesomeConfig<Config>(ID)(Config::class.java)

    }

    override fun onInitialize() {
        println("$NAME!")
        AwesomeCommands()
        // AwesomeDimensions()
        AwesomeEffects()
        AwesomeEnchantments()
        AwesomeGameRules()
        AwesomeItems()
        AwesomeMachines()
        AwesomeMaterials()
        AwesomeWorldGen()
        if (CONFIG.silkTouchSpawners) SilkTouchSpawners()
    }


    data class Config(
        @SerializedName("diamondDustFromCrushingCoalBlock") val diamondDustFromCrushingCoalBlock: Boolean = true,
        @SerializedName("experiencePotions") val experiencePotions: Boolean = true,
        @SerializedName("imprisoner") val imprisoner: Boolean = true,
        @SerializedName("magnetismEnchantment") val magnetismEnchantment: Boolean = true,
        @SerializedName("oreDropXpWithExperienceEnchantment") val oreDropXpWithExperienceEnchantment: Boolean = true,
        @SerializedName("randomiumWorldGen") val randomiumWorldGen: Boolean = true,
        @SerializedName("redstoneFluxFromRandomiumOre") val redstoneFluxFromRandomiumOre: Boolean = true,
        @SerializedName("redstoneFromCrushingNetherrack") val redstoneFromCrushingNetherrack: Boolean = true,
        @SerializedName("silkTouchSpawners") val silkTouchSpawners: Boolean = true,
        @SerializedName("trueInfinityBow") val trueInfinityBow: Boolean = true,
        @SerializedName("veinMiningEnchantment") val veinMiningEnchantment: Boolean = true,
        @SerializedName("villagersFollowEmeraldBlock") val villagersFollowEmeraldBlock: Boolean = true,
        @SerializedName("villagersInfiniteTrading") val villagersInfiniteTrading: Boolean = true,
    )

}
