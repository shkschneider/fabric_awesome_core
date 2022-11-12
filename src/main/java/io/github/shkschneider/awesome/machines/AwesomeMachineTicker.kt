package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.core.ext.getStacks
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.recipes.AwesomeRecipe
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class AwesomeMachineTicker(
    private val entity: AwesomeMachineBlockEntity,
    private val slots: InputOutput.Slots,
    private val recipes: List<AwesomeRecipe<out Inventory>>?,
) {

    private val inventory = entity as Inventory

    init {
        if (recipes != null) {
            check(recipes.isNotEmpty())
            check(recipes.all { it.inputs.isEmpty().not() && it.inputs.size == recipes.first().inputs.size })
        }
    }

    fun getInputs(): List<Pair<Int, ItemStack>> =
        inventory.getStacks().take(slots.inputs).mapIndexed { index, itemStack -> index to itemStack }

    fun getOutputs(): List<Pair<Int, ItemStack>> =
        inventory.getStacks().drop(slots.inputs).mapIndexed { index, itemStack -> slots.inputs + index to itemStack }

    fun getRecipe(): AwesomeRecipe<out Inventory>? =
        recipes?.firstOrNull { recipe ->
            (0 until slots.inputs).all { index ->
                getInputs()[index].second.item == recipe.inputs[index].item && getInputs()[index].second.count >= recipe.inputs[index].count
            } && getOutputs().map { it.second }.any { output ->
                output.isEmpty || (output.item == recipe.output.item && output.count + recipe.output.count <= output.maxCount)
            }
        }

    fun craft(recipe: AwesomeRecipe<out Inventory>) {
        getInputs().forEachIndexed { i, _ ->
            inventory.removeStack(i, recipe.inputs.first().count)
        }
        getOutputs().firstOrNull { it.second.isEmpty || it.second.count + recipe.output.count <= it.second.maxCount }?.let {
            inventory.setStack(it.first, recipe.output.copy().apply { count += it.second.count })
        }
    }

    operator fun invoke(world: World): Int {
        if (world.isClient) return 0
        with(entity) {
            if (recipes == null) {
                duration = 0
                progress = 0
                return -1
            }
            if (power == 0) {
                duration = 0
                progress = 0
                return -2
            }
            if (duration > 0) progress++
            val recipe = getRecipe() ?: run {
                duration = 0
                progress = 0
                return -3
            }
            if (duration == 0) {
                duration = recipe.time
                progress = 0
            } else if (progress >= duration) {
                craft(recipe)
                duration = 0
                progress = 0
            }
        }
        return entity.duration - entity.progress
    }

}
