package io.github.shkschneider.awesome.machines.recipes

import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot

class AwesomeRecipeHelper(
    private val inventory: Inventory,
    private val slots: Pair<Int, Int>,
    private val recipes: List<AwesomeRecipe<*>>,
) {

    init {
        check(inventory.size() >= 2)
        check(slots.first + slots.second <= inventory.size())
        check(recipes.isNotEmpty())
        if (recipes.any { it.fuel != null }) check(recipes.all { it.fuel != null })
        check(recipes.all { it.inputs.isEmpty().not() })
        check(recipes.all { it.output.count == recipes.first().output.count })
    }

    private val fueld = recipes.any { it.fuel != null }

    fun getFuel(): Pair<Int, ItemStack>? {
        if (recipes.all { it.fuel == null }) return null
        val i = slots.first - 1
        val slot = inventory.getStack(i)
        return (i to slot)
    }

    fun getInputs(): List<Pair<Int, ItemStack>> =
        (0 until slots.first - if (fueld) 1 else 0).mapIndexed { i, slot -> i to inventory.getStack(slot) }

    fun getOutputs(): List<Pair<Int, ItemStack>> =
        (slots.first until slots.first + slots.second).mapIndexed { i, slot ->  slots.first + i to inventory.getStack(slot) }

    fun getRecipe() =
        recipes.firstOrNull { recipe ->
            recipe.inputs.all { input ->
                getInputs().any { it.second.item == input.item && it.second.count >= input.count }
            }
        }?.takeIf { recipe ->
            getOutputs().any { it.second.isEmpty || (it.second.item == recipe.output.item && it.second.count + recipe.output.count <= it.second.maxCount) }
        }

    fun burn(recipe: AwesomeRecipe<*>): Boolean {
        val fuel = getFuel()
        return if (fuel == null) {
            true
        } else if (fuel.second.item == recipe.fuel && fuel.second.count > 0) {
            inventory.removeStack(fuel.first, 1)
            true
        } else {
            false
        }
    }

    fun craft(recipe: AwesomeRecipe<*>) {
        getInputs().forEachIndexed { i, _ ->
            inventory.removeStack(i, recipe.inputs.first().count)
        }
        getOutputs().firstOrNull { it.second.isEmpty || it.second.count + recipe.output.count <= it.second.maxCount }?.let {
            inventory.setStack(it.first, recipe.output.copy().apply { count += it.second.count })
        } ?: run {
            // FIXME
        }
    }

}