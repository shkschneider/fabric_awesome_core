package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipeType
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe

object InfuserRecipes {

    val INFUSING = AwesomeRecipeType<Recipe<InfuserBlock.Entity>>()

    val copperDust = AwesomeRecipe(INFUSING, null, listOf(ItemStack(AwesomeMaterials.crushedCopper, 2), ItemStack.EMPTY), ItemStack(AwesomeMaterials.copperDust, 2))
    val goldDust = AwesomeRecipe(INFUSING, null, listOf(ItemStack(AwesomeMaterials.crushedGold, 2), ItemStack.EMPTY), ItemStack(AwesomeMaterials.goldDust, 2))
    val ironDust = AwesomeRecipe(INFUSING, null, listOf(ItemStack(AwesomeMaterials.crushedIron, 2), ItemStack.EMPTY), ItemStack(AwesomeMaterials.ironDust, 2))

    val redstoneFluxDust = AwesomeRecipe(INFUSING, null, listOf(ItemStack(AwesomeMaterials.lapisDust, 1), ItemStack(AwesomeMaterials.redstoneDust, 1)), ItemStack(AwesomeMaterials.redstoneFlux, 2))

    operator fun invoke(): List<AwesomeRecipe<InfuserBlock.Entity>> = mutableListOf(
        copperDust,
        goldDust,
        ironDust,
        redstoneFluxDust,
    )

}