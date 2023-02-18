package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.core.AwesomeBlockEntity
import io.github.shkschneider.awesome.core.AwesomeRecipe
import io.github.shkschneider.awesome.core.ext.getStacks
import io.github.shkschneider.awesome.custom.InputOutput
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.state.property.Properties
import net.minecraft.world.World

class AwesomeMachineTicker<BE : AwesomeBlockEntity.WithInventory, SH : AwesomeMachineScreenHandler<BE>>(
    private val entity: AwesomeMachineBlockEntity<BE, SH>,
    private val io: InputOutput,
    private val recipes: List<AwesomeRecipe<out Inventory>>?,
) {

    private val inventory = entity as Inventory

    init {
        if (recipes != null) {
            check(recipes.isNotEmpty())
            check(recipes.all { it.inputs.isEmpty().not() && it.inputs.size == recipes.first().inputs.size })
        }
    }

    // I/O

    private fun getInputs(): List<ItemStack> =
        // remove 1 slot because fuel is the last input
        inventory.getStacks().take(io.inputs)

    private fun getFuel(): ItemStack =
        throw IllegalStateException()

    private fun getOutputs(): List<ItemStack> =
        inventory.getStacks().drop(io.inputs)

    private fun getRecipe(): AwesomeRecipe<out Inventory>? =
        recipes?.firstOrNull { recipe ->
            // recipe must have all its ingredients present in inputs and have room in any outputs
            recipe.inputs.all { ingredient ->
                getInputs().any { it.item == ingredient.item && it.count >= ingredient.count }
            } && getOutputs().any { output ->
                // only returns a recipe when there is output space
                output.isEmpty || (output.item == recipe.output.item && output.count + recipe.output.count <= output.maxCount)
            }
        }

    // State

    private fun on() {
        entity.setPropertyState(entity.state.with(Properties.LIT, true))
    }

    private fun off() {
        entity.setPropertyState(entity.state.with(Properties.LIT, false))
        entity.duration = 0
        entity.progress = 0
    }

    // Tick

    operator fun invoke(world: World): Int {
        if (world.isClient) return 0
        val recipe = getRecipe()
        if (recipe != null) {
            on()
            if (entity.duration > 0) entity.progress++
            if (entity.duration == 0) {
                entity.duration = recipe.time
                entity.progress = 0
            } else if (entity.progress >= entity.duration) {
                craft(recipe)
                entity.duration = recipe.time
                entity.progress = 0
            }
            return entity.duration - entity.progress
        } else {
            off()
            return 0
        }
    }

    private fun craft(recipe: AwesomeRecipe<out Inventory>) {
        getInputs().forEachIndexed { i, _ ->
            inventory.removeStack(i, recipe.inputs[i].count)
        }
        getOutputs().firstOrNull { it.item == recipe.output.item && it.count > 0 }?.let { stack ->
            // fill any not-maxed slot first
            stack.count += recipe.output.count
        } ?: inventory.getStacks().mapIndexed { slot, stack -> slot to stack }.firstOrNull { io.isOutput(it.first) && it.second.isEmpty }?.first?.let { slot ->
            // or any empty
            inventory.setStack(slot, recipe.output.copy())
        }
        entity.markDirty()
    }

}
