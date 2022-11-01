package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.AwesomeConfig
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipeType
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.Recipe

object InfuserRecipes {

    val INFUSING = AwesomeRecipeType<Recipe<InfuserBlock.Entity>>()

    val redstone = AwesomeRecipe(INFUSING, null, listOf(ItemStack(Items.STONE, 1), ItemStack(Items.RED_DYE, 1)), ItemStack(AwesomeMaterials.redstoneDust, 2))
    val redstoneFluxDust = AwesomeRecipe(INFUSING, null, listOf(ItemStack(AwesomeMaterials.lapisDust, 1), ItemStack(AwesomeMaterials.redstoneDust, 1)), ItemStack(AwesomeMaterials.redstoneFlux, 2))

    operator fun invoke(): List<AwesomeRecipe<InfuserBlock.Entity>> = mutableListOf(
        redstoneFluxDust,
    ).apply {
        if (AwesomeConfig.redstoneFromInfusingStoneAndRedDye) add(redstone)
    }

}