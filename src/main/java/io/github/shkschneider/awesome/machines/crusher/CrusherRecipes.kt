package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipeType
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.Recipe

object CrusherRecipes {

    val CRUSHING = AwesomeRecipeType<Recipe<CrusherBlock.Entity>>()

    val lapisLazuliDust = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.LAPIS_LAZULI, 1)), ItemStack(AwesomeMaterials.lapisLazuliDust, 9))

    operator fun invoke() = listOf(
        lapisLazuliDust
    )

}