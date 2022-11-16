package io.github.shkschneider.awesome

import com.google.gson.annotations.SerializedName
import io.github.shkschneider.awesome.blocks.AwesomeBlocks
import io.github.shkschneider.awesome.commands.AwesomeCommands
import io.github.shkschneider.awesome.core.AwesomeConfig
import io.github.shkschneider.awesome.core.AwesomeLogger
import io.github.shkschneider.awesome.core.AwesomeTime
import io.github.shkschneider.awesome.core.Minecraft
import io.github.shkschneider.awesome.custom.Dimensions
import io.github.shkschneider.awesome.custom.SilkTouchSpawners
import io.github.shkschneider.awesome.effects.AwesomeEffects
import io.github.shkschneider.awesome.enchantments.AwesomeEnchantments
import io.github.shkschneider.awesome.gamerules.AwesomeGameRules
import io.github.shkschneider.awesome.items.AwesomeItems
import io.github.shkschneider.awesome.machines.AwesomeMachines
import io.github.shkschneider.awesome.potions.AwesomePotions
import io.github.shkschneider.awesome.worldgen.AwesomeWorldGen
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

class Awesome : ModInitializer {

    companion object {

        val ID = Awesome::class.java.simpleName.lowercase()
        val NAME = ID.replaceFirstChar { it.uppercase() }

        val GROUP = FabricItemGroupBuilder.build(Identifier(ID, ID)) { ItemStack(AwesomeItems.Redstone.flux) }

        val CONFIG = AwesomeConfig<Config>(ID)(Config::class.java)

    }

    override fun onInitialize() {
        val ms = System.currentTimeMillis()
        AwesomeLogger.debug("Loading for ${Minecraft.VERSION}...")
        AwesomeBlocks()
        AwesomeCommands()
        AwesomeEffects()
        AwesomeEnchantments()
        AwesomeGameRules()
        AwesomeItems()
        AwesomeMachines()
        AwesomePotions()
        AwesomeTime()
        AwesomeWorldGen()
        Dimensions()
        SilkTouchSpawners()
        AwesomeLogger.info("Took ${System.currentTimeMillis() - ms}ms!")
    }


    data class Config(
        @SerializedName("commands") val commands: Boolean = true,
        @SerializedName("enchantments") val enchantments: Enchantments = Enchantments(),
        @SerializedName("entities") val entities: Entities = Entities(),
        @SerializedName("gamerules") val gameRules: GameRules = GameRules(),
        @SerializedName("items") val items: Items = Items(),
        @SerializedName("machines") val machines: Boolean = false,
        @SerializedName("potions") val potions: Potions = Potions(),
        @SerializedName("recipes") val recipes: Recipes = Recipes(),
        @SerializedName("splashBlackBackground") val splashBlackBackground: Boolean = true,
        @SerializedName("worldgen") val worldGen: WorldGen = WorldGen(),
    ) {

        data class Enchantments(
            @SerializedName("experience") val experience: Boolean = true,
            @SerializedName("magnetism") val magnetism: Boolean = true,
            @SerializedName("infinity") val infinity: Boolean = true,
            @SerializedName("veinMining") val veinMining: Boolean = true,
        )

        data class Entities(
            @SerializedName("villagersFollowEmeraldBlock") val villagersFollowEmeraldBlock: Boolean = true,
            @SerializedName("villagersInfiniteTrading") val villagersInfiniteTrading: Boolean = true,
        )

        data class GameRules(
            @SerializedName("oreXp") val oreXp: Boolean = true,
            @SerializedName("silkTouchSpawners") val silkTouchSpawners: Boolean = true,
        )

        data class Items(
            @SerializedName("imprisoner") val imprisoner: Boolean = true,
            @SerializedName("prospector") val prospector: Boolean = false,
        )

        data class Potions(
            @SerializedName("experience") val experience: Boolean = true,
        )

        data class Recipes(
            @SerializedName("diamondDustFromCrushingCoalBlock") val diamondDustFromCrushingCoalBlock: Boolean = true,
            @SerializedName("gravelFromCobblestone") val gravelFromCobblestone: Boolean = true,
            @SerializedName("redstoneFluxAsVanillaFuel") val redstoneFluxAsVanillaFuel: Boolean = true,
            @SerializedName("redstoneFluxFromRandomiumOre") val redstoneFluxFromRandomiumOre: Boolean = true,
            @SerializedName("redstoneFromCrushingNetherrack") val redstoneFromCrushingNetherrack: Boolean = true,
            @SerializedName("sandFromGravel") val sandFromGravel: Boolean = true,
        )

        data class WorldGen(
            @SerializedName("randomium") val randomium: Boolean = true,
        )

    }

}
