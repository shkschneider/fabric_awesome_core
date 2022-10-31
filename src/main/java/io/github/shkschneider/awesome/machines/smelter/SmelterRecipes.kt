package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipeType
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.Recipe

object SmelterRecipes {

    val SMELTING = AwesomeRecipeType<Recipe<SmelterBlock.Entity>>()

    val redstoneFlux = AwesomeRecipe(SMELTING, Items.COAL, listOf(ItemStack(AwesomeMaterials.redstoneFluxDust, 1)), ItemStack(AwesomeMaterials.redstoneFlux, 1))

    operator fun invoke() = listOf(
        redstoneFlux
    )

}