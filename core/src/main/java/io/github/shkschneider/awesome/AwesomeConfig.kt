package io.github.shkschneider.awesome

import com.google.gson.annotations.SerializedName

data class AwesomeConfig(
    @SerializedName("commands") val commands: Boolean = true,
    @SerializedName("enchantments") val enchantments: Enchantments = Enchantments(),
    @SerializedName("entities") val entities: Entities = Entities(),
    @SerializedName("gamerules") val gameRules: GameRules = GameRules(),
    @SerializedName("items") val items: Items = Items(),
    @SerializedName("machines") val machines: Boolean = true,
    @SerializedName("potions") val potions: Potions = Potions(),
    @SerializedName("recipes") val recipes: Recipes = Recipes(),
    @SerializedName("splashBlackBackground") val splashBlackBackground: Boolean = true,
    @SerializedName("worldgen") val worldGen: WorldGen = WorldGen(),
) {

    data class Enchantments(
        @SerializedName("aspects") val aspects: Boolean = true,
        @SerializedName("experience") val experience: Boolean = true,
        @SerializedName("infinity") val infinity: Boolean = true,
        @SerializedName("magnetism") val magnetism: Boolean = true,
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
        @SerializedName("prospector") val prospector: Boolean = true,
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
