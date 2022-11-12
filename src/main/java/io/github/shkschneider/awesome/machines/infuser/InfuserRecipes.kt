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
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Coal.powder, 1), ItemStack(AwesomeItems.Coal.powder, 1)), time = 20, ItemStack(AwesomeItems.Coal.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Copper.powder, 1), ItemStack(AwesomeItems.Copper.powder, 1)), time = 20, ItemStack(AwesomeItems.Copper.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Diamond.powder, 1), ItemStack(AwesomeItems.Diamond.powder, 1)), time = 20, ItemStack(AwesomeItems.Diamond.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Emerald.powder, 1), ItemStack(AwesomeItems.Emerald.powder, 1)), time = 20, ItemStack(AwesomeItems.Emerald.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Gold.powder, 1), ItemStack(AwesomeItems.Gold.powder, 1)), time = 20, ItemStack(AwesomeItems.Gold.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Iron.powder, 1), ItemStack(AwesomeItems.Iron.powder, 1)), time = 20, ItemStack(AwesomeItems.Iron.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Lapis.powder, 1), ItemStack(AwesomeItems.Lapis.powder, 1)), time = 20, ItemStack(AwesomeItems.Lapis.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Quartz.powder, 1), ItemStack(AwesomeItems.Quartz.powder, 1)), time = 20, ItemStack(AwesomeItems.Quartz.dust, 2)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Redstone.powder, 1), ItemStack(AwesomeItems.Redstone.powder, 1)), time = 20, ItemStack(AwesomeItems.Redstone.dust, 2)),
        // RedstoneFlux
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Lapis.dust, 1), ItemStack(AwesomeItems.Redstone.dust, 1)), time = 20, ItemStack(AwesomeItems.Redstone.flux, 2)),
    )

}
