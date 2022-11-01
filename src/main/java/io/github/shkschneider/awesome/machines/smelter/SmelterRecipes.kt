package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipeType
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.Recipe

object SmelterRecipes {

    val SMELTING = AwesomeRecipeType<Recipe<SmelterBlock.Entity>>()

    // Vanilla

    val copperIngotFromDust = AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.copperDust, 1)), ItemStack(Items.COPPER_INGOT, 1))
    val diamondFromDust = AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.diamondDust, 1)), ItemStack(Items.DIAMOND, 1))
    val emeraldFromDust = AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.emeraldDust, 1)), ItemStack(Items.EMERALD, 1))
    val goldIngotFromDust = AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.goldDust, 1)), ItemStack(Items.GOLD_INGOT, 1))
    val ironIngotFromDust = AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.ironDust, 1)), ItemStack(Items.IRON_INGOT, 1))
    val lapisFromDust = AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.lapisDust, 1)), ItemStack(Items.LAPIS_LAZULI, 1))

    operator fun invoke(): List<AwesomeRecipe<SmelterBlock.Entity>> = listOf(
        // Vanilla
        copperIngotFromDust,
        diamondFromDust,
        emeraldFromDust,
        goldIngotFromDust,
        ironIngotFromDust,
        lapisFromDust,
    )

}