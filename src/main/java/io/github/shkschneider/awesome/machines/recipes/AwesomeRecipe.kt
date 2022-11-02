package io.github.shkschneider.awesome.machines.recipes

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.machines.crusher.Crusher
import net.minecraft.inventory.Inventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.util.Identifier
import net.minecraft.world.World

class AwesomeRecipe<T : Inventory>(
    private val type: AwesomeRecipeType<Recipe<T>>,
    val inputs: List<ItemStack>,
    val fuel: Item?,
    private val output: ItemStack,
) : Recipe<T> {

    override fun matches(inventory: T, world: World): Boolean {
        if (world.isClient) return false
        return true // FIXME
    }

    override fun craft(inventory: T): ItemStack {
        return output
    }

    override fun fits(width: Int, height: Int): Boolean {
        return true
    }

    override fun getOutput(): ItemStack {
        return output
    }

    override fun getId(): Identifier {
        return AwesomeUtils.identifier(Crusher.ID)
    }

    override fun getSerializer(): RecipeSerializer<*> {
        throw NotImplementedError()
    }

    override fun getType(): RecipeType<*> {
        return type
    }

}
