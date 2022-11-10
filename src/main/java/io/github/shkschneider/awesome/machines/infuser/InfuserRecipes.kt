package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.items.AwesomeItems
import io.github.shkschneider.awesome.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.recipes.AwesomeRecipeType
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe

object InfuserRecipes {

    private val INFUSING = AwesomeRecipeType<Recipe<InfuserBlock.Entity>>()

    operator fun invoke(): List<AwesomeRecipe<InfuserBlock.Entity>> = mutableListOf(
        // Powder -> Dust
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Coal.powder, 2), ItemStack.EMPTY), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Coal.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Copper.powder, 2), ItemStack.EMPTY), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Copper.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Diamond.powder, 2), ItemStack.EMPTY), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Diamond.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Emerald.powder, 2), ItemStack.EMPTY), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Emerald.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Gold.powder, 2), ItemStack.EMPTY), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Gold.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Iron.powder, 2), ItemStack.EMPTY), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Iron.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Lapis.powder, 2), ItemStack.EMPTY), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Lapis.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Quartz.powder, 2), ItemStack.EMPTY), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Quartz.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Redstone.powder, 2), ItemStack.EMPTY), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Redstone.dust, 2)),
        // RedstoneFlux
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Lapis.dust, 1), ItemStack(AwesomeItems.Redstone.dust, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Redstone.flux, 2)),
    )

}
