package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipeType
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.Recipe

object SmelterRecipes {

    val SMELTING = AwesomeRecipeType<Recipe<SmelterBlock.Entity>>()

    private val RECIPES = listOf(
        // Dust -> Ingot/Gem
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.coalDust, 1)), ItemStack(Items.COAL, 1)),
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.copperDust, 1)), ItemStack(Items.COPPER_INGOT, 1)),
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.diamondDust, 1)), ItemStack(Items.DIAMOND, 1)),
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.emeraldDust, 1)), ItemStack(Items.EMERALD, 1)),
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.goldDust, 1)), ItemStack(Items.GOLD_INGOT, 1)),
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.ironDust, 1)), ItemStack(Items.IRON_INGOT, 1)),
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.lapisDust, 1)), ItemStack(Items.LAPIS_LAZULI, 1)),
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(AwesomeMaterials.quartzDust, 1)), ItemStack(Items.QUARTZ, 1)),
        // Raw -> Ingot
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(Items.RAW_COPPER, 1)), ItemStack(Items.COPPER_INGOT, 1)),
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(Items.RAW_GOLD, 1)), ItemStack(Items.GOLD_INGOT, 1)),
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(Items.RAW_IRON, 1)), ItemStack(Items.IRON_INGOT, 1)),
        // RawBlock -> Block
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(Items.RAW_COPPER_BLOCK, 1)), ItemStack(Items.COPPER_BLOCK, 1)),
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(Items.RAW_GOLD_BLOCK, 1)), ItemStack(Items.GOLD_BLOCK, 1)),
        AwesomeRecipe(SMELTING, AwesomeMaterials.redstoneFlux, listOf(ItemStack(Items.RAW_IRON_BLOCK, 1)), ItemStack(Items.IRON_BLOCK, 1)),
    )

    operator fun invoke() = RECIPES

}