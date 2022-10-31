package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipeType
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe

object InfuserRecipes {

    val INFUSING = AwesomeRecipeType<Recipe<InfuserBlock.Entity>>()

    val redstoneFluxDust = AwesomeRecipe(INFUSING, null, listOf(ItemStack(AwesomeMaterials.lapisLazuliDust, 1), ItemStack(AwesomeMaterials.redstoneDust, 1)), ItemStack(AwesomeMaterials.redstoneFluxDust, 1))

    operator fun invoke() = listOf(
        redstoneFluxDust
    )

}