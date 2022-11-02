package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipe
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack

class AwesomeMachineTicker(
    private val entity: AwesomeMachineBlockEntity,
    private val slots: AwesomeMachine.Slots,
    private val recipes: List<AwesomeRecipe<out Inventory>>,
) {

    companion object {

        val INPUT = 200 // ticks
        val OUTPUT = 20 // ticks

    }

    private val inventory = entity as Inventory

    init {
        check(recipes.isNotEmpty())
        check(recipes.all { it.inputs.isEmpty().not() && it.inputs.size == recipes.first().inputs.size })
    }

    fun getFuel(): Pair<Int, ItemStack>? {
        if (slots.fuel == null) return null
        return slots.inputs to inventory.getStack(slots.inputs)
    }

    fun getInputs(): List<Pair<Int, ItemStack>> =
        (0 until slots.inputs).mapIndexed { i, slot -> i to inventory.getStack(slot) }

    fun getOutputs(): List<Pair<Int, ItemStack>> {
        val offset = slots.inputs + if (slots.fuel != null) 1 else 0
        return (offset until slots.size).mapIndexed { i, slot ->  offset + i to inventory.getStack(slot) }
    }

    fun getRecipe() =
        recipes.firstOrNull { recipe ->
            recipe.inputs.all { input ->
                getInputs().any { it.second.item == input.item && it.second.count >= input.count }
            } && recipe.inputs.sumOf { it.count } <= getInputs().sumOf { it.second.count }
        }?.takeIf { recipe ->
            getOutputs().any { it.second.isEmpty || (it.second.item == recipe.output.item && it.second.count + recipe.output.count <= it.second.maxCount) }
        }

    fun burn(recipe: AwesomeRecipe<out Inventory>): Boolean {
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
                        if (burn(recipe)) {
                            entity.inputProgress = INPUT
                            on()
                        } else {
                            off()
                            return
                        }
                    }
                    entity.outputProgress = OUTPUT
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
        entity.inputProgress = AwesomeUtils.TICK
        val recipe = getRecipe()
        if (recipe != null) {
            when {
                entity.outputProgress < 0 -> {
                    on()
                    entity.outputProgress = OUTPUT
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
