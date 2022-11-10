package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.core.Minecraft
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.recipes.AwesomeRecipe
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack

class AwesomeMachineTicker(
    private val entity: AwesomeMachineBlockEntity,
    private val slots: InputOutput.Slots,
    private val recipes: List<AwesomeRecipe<out Inventory>>,
) {

    companion object {

        val INPUT = 200 // ticks

    }

    private val inventory = entity as Inventory

    init {
        check(recipes.isNotEmpty())
        check(recipes.all { it.inputs.isEmpty().not() && it.inputs.size == recipes.first().inputs.size })
    }

    fun hasPower() = true // TODO redstone

    fun getInputs(): List<Pair<Int, ItemStack>> =
        (0 until slots.inputs).mapIndexed { i, slot -> i to inventory.getStack(slot) }

    fun getOutputs(): List<Pair<Int, ItemStack>> =
        (slots.inputs until slots.size).mapIndexed { i, slot ->  slots.inputs + i to inventory.getStack(slot) }

    fun getRecipe() =
        recipes.firstOrNull { recipe ->
            recipe.inputs.all { input ->
                getInputs().any { it.second.item == input.item && it.second.count >= input.count }
            } && recipe.inputs.sumOf { it.count } <= getInputs().sumOf { it.second.count }
        }?.takeIf { recipe ->
            getOutputs().any { it.second.isEmpty || (it.second.item == recipe.output.item && it.second.count + recipe.output.count <= it.second.maxCount) }
        }

    fun craft(recipe: AwesomeRecipe<out Inventory>) {
        getInputs().forEachIndexed { i, _ ->
            inventory.removeStack(i, recipe.inputs.first().count)
        }
        getOutputs().firstOrNull { it.second.isEmpty || it.second.count + recipe.output.count <= it.second.maxCount }?.let {
            inventory.setStack(it.first, recipe.output.copy().apply { count += it.second.count })
        } ?: run {
            // FIXME
        }
    }

    operator fun invoke(on: () -> Unit, off: () -> Unit) {
        if (entity.inputProgress > 0) entity.inputProgress--
        val recipe = getRecipe()
        if (recipe != null) {
            when {
                entity.outputProgress < 0 -> {
                    if (entity.inputProgress <= 0) {
                        if (hasPower()) {
                            entity.inputProgress = INPUT
                            on()
                        } else {
                            off()
                            return
                        }
                    }
                    entity.outputProgress = recipe.time
                }
                entity.outputProgress == 0 -> {
                    craft(recipe)
                    entity.outputProgress = -1
                }
                else -> entity.outputProgress--
            }
        } else {
            entity.outputProgress = -1
            off()
        }
    }

    fun withoutFuel(on: () -> Unit, off: () -> Unit) {
        entity.inputProgress = Minecraft.TICK
        val recipe = getRecipe()
        if (recipe != null) {
            when {
                entity.outputProgress < 0 -> {
                    on()
                    entity.outputProgress = recipe.time
                }
                entity.outputProgress == 0 -> {
                    craft(recipe)
                    entity.outputProgress = -1
                }
                else -> entity.outputProgress--
            }
        } else {
            entity.outputProgress = -1
            off()
        }
    }

}
