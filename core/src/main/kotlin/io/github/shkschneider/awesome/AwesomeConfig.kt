package io.github.shkschneider.awesome

import com.google.gson.annotations.SerializedName

data class AwesomeConfig(
    @SerializedName("crystals") val crystals: Crystals = Crystals(),
    @SerializedName("enchantments") val enchantments: Enchantments = Enchantments(),
    @SerializedName("experience") val experience: Experience = Experience(),
    @SerializedName("extras") val extras: Extras = Extras(),
    @SerializedName("machines") val machines: Machines = Machines(),
) {

    data class Crystals(
        @SerializedName("coal") val coal: Boolean = true,
        @SerializedName("copper") val copper: Boolean = true,
        @SerializedName("diamond") val diamond: Boolean = true,
        @SerializedName("emerald") val emerald: Boolean = true,
        @SerializedName("gold") val gold: Boolean = true,
        @SerializedName("iron") val iron: Boolean = true,
        @SerializedName("lapis") val lapis: Boolean = true,
        @SerializedName("netherite") val netherite: Boolean = true,
        @SerializedName("quartz") val quartz: Boolean = true,
        @SerializedName("redstone") val redstone: Boolean = true,
    )

    data class Enchantments(
        @SerializedName("iceAspect") val iceAspect: Boolean = true,
        @SerializedName("infinity") val infinity: Boolean = true,
        @SerializedName("magnetism") val magnetism: Boolean = true,
        @SerializedName("paralysis") val paralysis: Boolean = true,
        @SerializedName("poisonAspect") val poisonAspect: Boolean = true,
        @SerializedName("silkTouchSpawners") val silkTouchSpawners: Boolean = true,
        @SerializedName("sixthSense") val sixthSense: Boolean = true,
        @SerializedName("unbreakable") val unbreakable: Boolean = true,
        @SerializedName("veinMining") val veinMining: Boolean = true,
    )

    data class Experience(
        @SerializedName("obelisk") val obelisk: Boolean = true,
        @SerializedName("oneXpPerBlock") val oneXpPerBlock: Boolean = true,
        @SerializedName("potions") val potions: Boolean = true,
    )

    data class Extras(
        @SerializedName("baguette") val baguette: Boolean = true,
        @SerializedName("crate") val crate: Boolean = true,
        @SerializedName("elevator") val elevator: Boolean = true,
        @SerializedName("entities") val entities: Boolean = true,
        @SerializedName("gameRulesOverrides") val gameRulesOverrides: Boolean = false,
        @SerializedName("herobrine") val herobrine: Boolean = false,
        @SerializedName("nadirLengthInDays") val nadirLengthInDays: Float = 0F,
        @SerializedName("playerHeads") val playerHeads: Boolean = true,
        @SerializedName("pvp") val pvp: Boolean = true,
        @SerializedName("randomium") val randomium: Boolean = true,
        @SerializedName("rope") val rope: Boolean = true,
        @SerializedName("scythe") val scythe: Boolean = true,
        @SerializedName("sleepingHeals") val sleepingHeals: Boolean = true,
        @SerializedName("spawnEggs") val spawnEggs: Boolean = true,
        @SerializedName("spongesInLava") val spongesInLava: Boolean = true,
        @SerializedName("totemFromInventory") val totemFromInventory: Boolean = true,
        @SerializedName("trashSlot") val trashSlot: Boolean = true,
        @SerializedName("unlockRecipes") val unlockRecipes: Boolean = true,
        @SerializedName("villagersFollowEmeraldBlock") val villagersFollowEmeraldBlock: Boolean = true,
        @SerializedName("villagersInfiniteTrading") val villagersInfiniteTrading: Boolean = true,
        @SerializedName("void") val void: Boolean = true,
        @SerializedName("zenithLengthInDays") val zenithLengthInDays: Float = 0.5F,
    )

    data class Machines(
        @SerializedName("diamondDustFromCrushingCoalBlock") val diamondDustFromCrushingCoalBlock: Boolean = true,
        @SerializedName("fluxAsVanillaFuel") val fluxAsVanillaFuel: Boolean = true,
        @SerializedName("gravelFromCobblestone") val gravelFromCobblestone: Boolean = true,
        @SerializedName("imprisoner") val imprisoner: Boolean = true,
        @SerializedName("moreNetherite") val moreNetherite: Boolean = true,
        @SerializedName("prospector") val prospector: Boolean = true,
        @SerializedName("quarry") val quarry: Boolean = true,
        @SerializedName("redstoneFromCrushingNetherrack") val redstoneFromCrushingNetherrack: Boolean = true,
        @SerializedName("sandFromGravel") val sandFromGravel: Boolean = true,
    )

}
