package io.github.shkschneider.awesome

import com.google.gson.annotations.SerializedName

data class AwesomeConfig(
    @SerializedName("enchantments") val enchantments: Enchantments = Enchantments(),
    @SerializedName("extras") val extras: Extras = Extras(),
    @SerializedName("machines") val machines: Machines = Machines(),
) {

    data class Enchantments(
        @SerializedName("aspects") val aspects: Boolean = true,
        @SerializedName("experience") val experience: Boolean = true,
        @SerializedName("infinity") val infinity: Boolean = true,
        @SerializedName("magnetism") val magnetism: Boolean = true,
        @SerializedName("oreXp") val oreXp: Boolean = true,
        @SerializedName("silkTouchSpawners") val silkTouchSpawners: Boolean = true,
        @SerializedName("unbreakable") val unbreakable: Boolean = true,
        @SerializedName("veinMining") val veinMining: Boolean = true,
    )

    data class Extras(
        @SerializedName("baguette") val baguette: Boolean = true,
        @SerializedName("blocks") val blocks: Boolean = true,
        @SerializedName("crate") val crate: Boolean = true,
        @SerializedName("entities") val entities: Boolean = true,
        @SerializedName("gameRulesOverrides") val gameRulesOverrides: Boolean = false,
        @SerializedName("nadirLengthInDays") val nadirLengthInDays: Float = 0F,
        @SerializedName("playerHeads") val playerHeads: Boolean = true,
        @SerializedName("pvp") val pvp: Boolean = true,
        @SerializedName("sleepingHeals") val sleepingHeals: Boolean = true,
        @SerializedName("spawnEggs") val spawnEggs: Boolean = true,
        @SerializedName("spongesInLava") val spongesInLava: Boolean = true,
        @SerializedName("totemFromInventory") val totemFromInventory: Boolean = true,
        @SerializedName("trashSlot") val trashSlot: Boolean = true,
        @SerializedName("unlockRecipes") val unlockRecipes: Boolean = true,
        @SerializedName("villagersFollowEmeraldBlock") val villagersFollowEmeraldBlock: Boolean = true,
        @SerializedName("villagersInfiniteTrading") val villagersInfiniteTrading: Boolean = true,
        @SerializedName("zenithLengthInDays") val zenithLengthInDays: Float = 0.5F,
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
