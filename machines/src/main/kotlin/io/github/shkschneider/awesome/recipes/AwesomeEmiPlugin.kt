package io.github.shkschneider.awesome.recipes

import dev.emi.emi.api.EmiPlugin
import dev.emi.emi.api.EmiRegistry
import dev.emi.emi.api.recipe.EmiRecipe
import dev.emi.emi.api.recipe.EmiRecipeCategory
import dev.emi.emi.api.render.EmiTexture
import dev.emi.emi.api.stack.EmiIngredient
import dev.emi.emi.api.stack.EmiStack
import dev.emi.emi.api.widget.WidgetHolder
import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.core.AwesomeRecipe
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.crusher.Crusher
import io.github.shkschneider.awesome.machines.infuser.Infuser
import io.github.shkschneider.awesome.machines.refinery.Refinery
import io.github.shkschneider.awesome.machines.smelter.Smelter
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.inventory.Inventory
import net.minecraft.recipe.Ingredient
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
class AwesomeEmiPlugin : EmiPlugin {

    override fun register(registry: EmiRegistry) {
        addWorkstationAndRecipes(registry, AwesomeMachines.crusher, Crusher.RECIPES)
        addWorkstationAndRecipes(registry, AwesomeMachines.infuser, Infuser.RECIPES)
        addWorkstationAndRecipes(registry, AwesomeMachines.refinery, Refinery.RECIPES)
        addWorkstationAndRecipes(registry, AwesomeMachines.smelter, Smelter.RECIPES)
    }

    private fun addWorkstationAndRecipes(registry: EmiRegistry, machine: AwesomeMachine<*, *, *>, recipes: List<AwesomeRecipe<*>>) {
        with(registry) {
            val category = EmiRecipeCategory(machine.id, EmiStack.of(machine.block.asItem()))
            addCategory(category)
            addWorkstation(category, EmiStack.of(machine.block.asItem()))
            recipes.forEach { recipe ->
                addRecipe(AwesomeEmiRecipe(category, recipe))
            }
        }
    }

}

class AwesomeEmiRecipe(
    private val category: EmiRecipeCategory,
    private val recipe: AwesomeRecipe<out Inventory>,
) : EmiRecipe {

    override fun getId(): Identifier =
        // modId:machine_input_output_n
        AwesomeUtils.identifier("${category.id.path}_${recipe.id.path}")

    override fun getCategory(): EmiRecipeCategory =
        category

    override fun getInputs(): MutableList<EmiIngredient> =
        recipe.inputs.map { EmiIngredient.of(Ingredient.ofStacks(it), it.count.toLong()) }.toMutableList()

    override fun getOutputs(): MutableList<EmiStack> =
        listOf(EmiStack.of(recipe.output)).toMutableList()

    override fun getDisplayWidth(): Int = 76

    override fun getDisplayHeight(): Int = 18 * recipe.inputs.size

    override fun addWidgets(holder: WidgetHolder) {
        with(holder) {
            // 1px + 16px + 1px = 18
            inputs.forEachIndexed { index, input ->
                addSlot(input, 0, index * 18)
            }
            if (outputs.size == 1) {
                addTexture(EmiTexture.EMPTY_ARROW, 26, holder.height / 2 - 18 / 2) // 24x17
                addSlot(outputs.first(), 58, holder.height / 2 - 18 / 2).recipeContext(this@AwesomeEmiRecipe)
            } else {
                addTexture(EmiTexture.EMPTY_ARROW, 26, 1)
                inputs.forEachIndexed { index, output ->
                    addSlot(output, 58, 0 + index * 18).recipeContext(this@AwesomeEmiRecipe)
                }
            }
        }
    }

}
