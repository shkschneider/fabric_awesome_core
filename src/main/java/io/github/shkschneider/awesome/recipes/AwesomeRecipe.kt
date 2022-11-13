package io.github.shkschneider.awesome.recipes

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.AwesomeUtils
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.util.Identifier
import net.minecraft.world.World

class AwesomeRecipe<T : Inventory>(
    private val type: AwesomeRecipeType<AwesomeRecipe<T>>,
    val inputs: List<ItemStack>,
    val time: Int, // ticks
    private val output: ItemStack,
) : Recipe<T> {

    override fun matches(inventory: T, world: World): Boolean {
        if (world.isClient) return false
        return true // FIXME
    }

    override fun craft(inventory: T): ItemStack =
        output

    override fun fits(width: Int, height: Int): Boolean =
        true

    override fun getOutput(): ItemStack =
        output

    override fun getId(): Identifier =
        AwesomeUtils.identifier("${Awesome.ID}_${output.item.name.string}")

    override fun getSerializer(): RecipeSerializer<*> {
        throw NotImplementedError()
    }

    override fun getType(): RecipeType<AwesomeRecipe<T>> =
        type

}
