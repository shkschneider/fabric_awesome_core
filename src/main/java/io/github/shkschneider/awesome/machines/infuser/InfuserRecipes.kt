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
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.Coal.powder, 2), ItemStack.EMPTY), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Coal.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.Copper.powder, 2), ItemStack.EMPTY), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Copper.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.Diamond.powder, 2), ItemStack.EMPTY), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Diamond.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.Emerald.powder, 2), ItemStack.EMPTY), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Emerald.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.Gold.powder, 2), ItemStack.EMPTY), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Gold.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.Iron.powder, 2), ItemStack.EMPTY), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Iron.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.Lapis.powder, 2), ItemStack.EMPTY), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Lapis.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.Quartz.powder, 2), ItemStack.EMPTY), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Quartz.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.Redstone.powder, 2), ItemStack.EMPTY), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Redstone.dust, 2)),
        // RedstoneFlux
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeMaterials.Lapis.dust, 1), ItemStack(AwesomeMaterials.Redstone.dust, 1)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Redstone.flux, 2)),
    )

}
