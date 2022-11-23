package io.github.shkschneider.awesome

import com.google.gson.annotations.SerializedName

data class AwesomeConfig(
    @SerializedName("commands") val commands: Boolean = true,
    @SerializedName("core") val core: Core = Core(),
    @SerializedName("darkSplashScreen") val darkSplashScreen: Boolean = true,
    @SerializedName("enchantments") val enchantments: Enchantments = Enchantments(),
    @SerializedName("machines") val machines: Machines = Machines(),
) {

    data class Core(
        @SerializedName("villagersFollowEmeraldBlock") val villagersFollowEmeraldBlock: Boolean = true,
        @SerializedName("villagersInfiniteTrading") val villagersInfiniteTrading: Boolean = true,
        @SerializedName("oreXp") val oreXp: Boolean = true,
        @SerializedName("pvp") val pvp: Boolean = true,
        @SerializedName("silkTouchSpawners") val silkTouchSpawners: Boolean = true,
        @SerializedName("sleepingHeals") val sleepingHeals: Boolean = true,
    )

    data class Enchantments(
        @SerializedName("aspects") val aspects: Boolean = true,
        @SerializedName("experience") val experience: Boolean = true,
        @SerializedName("infinity") val infinity: Boolean = true,
        @SerializedName("magnetism") val magnetism: Boolean = true,
        @SerializedName("veinMining") val veinMining: Boolean = true,
    )

    data class Machines(
        @SerializedName("imprisoner") val imprisoner: Boolean = true,
        @SerializedName("prospector") val prospector: Boolean = true,
        @SerializedName("diamondDustFromCrushingCoalBlock") val diamondDustFromCrushingCoalBlock: Boolean = true,
        @SerializedName("gravelFromCobblestone") val gravelFromCobblestone: Boolean = true,
        @SerializedName("redstoneFluxAsVanillaFuel") val redstoneFluxAsVanillaFuel: Boolean = true,
        @SerializedName("redstoneFluxFromRandomiumOre") val redstoneFluxFromRandomiumOre: Boolean = true,
        @SerializedName("redstoneFromCrushingNetherrack") val redstoneFromCrushingNetherrack: Boolean = true,
        @SerializedName("sandFromGravel") val sandFromGravel: Boolean = true,
        @SerializedName("randomium") val randomium: Boolean = true,
    )

}
