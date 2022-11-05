package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.recipes.AwesomeRecipeType
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe

object InfuserRecipes {

    private val INFUSING = AwesomeRecipeType<Recipe<InfuserBlock.Entity>>()

    operator fun invoke(): List<AwesomeRecipe<InfuserBlock.Entity>> = mutableListOf(
        // Powder -> Dust
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.coalPowder, 2), ItemStack.EMPTY), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.coalDust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.copperPowder, 2), ItemStack.EMPTY), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.copperDust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.diamondPowder, 2), ItemStack.EMPTY), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.diamondDust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.emeraldPowder, 2), ItemStack.EMPTY), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.emeraldDust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.goldPowder, 2), ItemStack.EMPTY), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.goldDust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.ironPowder, 2), ItemStack.EMPTY), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.ironDust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.lapisPowder, 2), ItemStack.EMPTY), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.lapisDust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.quartzPowder, 2), ItemStack.EMPTY), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.quartzDust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.redstonePowder, 2), ItemStack.EMPTY), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.redstoneDust, 2)),
        // RedstoneFlux
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.lapisDust, 1), ItemStack(AwesomeMaterials.redstoneDust, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.redstoneFlux, 2)),
    )

}
