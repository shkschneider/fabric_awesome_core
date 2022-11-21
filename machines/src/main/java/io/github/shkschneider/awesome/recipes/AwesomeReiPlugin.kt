package io.github.shkschneider.awesome.recipes

import io.github.shkschneider.awesome.core.AwesomeRecipe
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.machines.crusher.CrusherRecipes
import io.github.shkschneider.awesome.machines.infuser.InfuserRecipes
import io.github.shkschneider.awesome.machines.refinery.RefineryRecipes
import io.github.shkschneider.awesome.machines.smelter.SmelterRecipes
import me.shedaniel.math.Point
import me.shedaniel.math.Rectangle
import me.shedaniel.rei.api.client.gui.Renderer
import me.shedaniel.rei.api.client.gui.widgets.Widget
import me.shedaniel.rei.api.client.gui.widgets.Widgets
import me.shedaniel.rei.api.client.plugins.REIClientPlugin
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry
import me.shedaniel.rei.api.client.registry.display.DisplayCategory
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.display.basic.BasicDisplay
import me.shedaniel.rei.api.common.entry.EntryIngredient
import me.shedaniel.rei.api.common.util.EntryIngredients
import me.shedaniel.rei.api.common.util.EntryStacks
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.recipe.Ingredient
import net.minecraft.text.Text
import kotlin.math.max

@Environment(EnvType.CLIENT)
class AwesomeReiPlugin : REIClientPlugin {

    private val crusher: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeMachines.crusher.id)
    private val infuser: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeMachines.infuser.id)
    private val refinery: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeMachines.refinery.id)
    private val smelter: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeMachines.smelter.id)

    override fun registerCategories(registry: CategoryRegistry) {
        addWorkstation(registry, crusher, AwesomeMachines.crusher)
        addWorkstation(registry, infuser, AwesomeMachines.infuser)
        addWorkstation(registry, refinery, AwesomeMachines.refinery)
        addWorkstation(registry, smelter, AwesomeMachines.smelter)
    }

    override fun registerDisplays(registry: DisplayRegistry) {
        CrusherRecipes().forEach { registry.add(AwesomeReiDisplay(crusher, it)) }
        InfuserRecipes().forEach { registry.add(AwesomeReiDisplay(infuser, it)) }
        RefineryRecipes().forEach { registry.add(AwesomeReiDisplay(refinery, it)) }
        SmelterRecipes().forEach { registry.add(AwesomeReiDisplay(smelter, it)) }
    }

    private fun addWorkstation(registry: CategoryRegistry, category: CategoryIdentifier<AwesomeReiDisplay>, machine: AwesomeMachine<*, *, *>) {
        registry.add(AwesomeReiCategory(machine))
        registry.addWorkstations(category, EntryStacks.of(machine.block.asItem()))
    }

}

class AwesomeReiCategory(
    private val machine: AwesomeMachine<*, *, *>,
) : DisplayCategory<AwesomeReiDisplay> {

    private val max: Int = max(machine.ports.inputs.first, machine.ports.outputs.first)

    override fun getCategoryIdentifier(): CategoryIdentifier<out AwesomeReiDisplay> =
        CategoryIdentifier.of(machine.id.namespace, machine.id.path)

    override fun getTitle(): Text = Text.translatable(machine.block.translationKey)

    override fun getIcon(): Renderer = EntryStacks.of(machine.block.asItem())

    override fun setupDisplay(display: AwesomeReiDisplay, bounds: Rectangle): MutableList<Widget> = buildList<Widget> {
        val inputs = display.inputEntries.flatMap { it.stream().toList() }.toList()
        val outputs = display.outputEntries.flatMap { it.stream().toList() }.toList()
        add(Widgets.createRecipeBase(bounds))
        // 1px + 16px + 1px = 18
        inputs.forEachIndexed { index, input ->
            add(Widgets.createSlot(Point(bounds.x + bounds.width / 2 - 24 / 2 - 18 * 2, bounds.y + 18 / 2 + 18 * index)).entry(input).markInput())
        }
        add(Widgets.createArrow(Point(bounds.centerX - 24 / 2, bounds.centerY - 18 / 2))) // 24x17
        add(Widgets.createSlot(Point(bounds.centerX + 24 / 2 + 18 * 1, bounds.centerY - 18 / 2)).entries(outputs).markInput())
    }.toMutableList()

    override fun getDisplayHeight(): Int =
        18 / 2 + 18 * max + 18 / 2

}

class AwesomeReiDisplay(
    private val category: CategoryIdentifier<*>,
    val recipe: AwesomeRecipe<*>,
) : BasicDisplay(
    EntryIngredients.ofIngredients(recipe.ingredients),
    mutableListOf(EntryIngredients.of(recipe.output)),
) {

    override fun getCategoryIdentifier(): CategoryIdentifier<*> =
        category

    override fun getInputEntries(): MutableList<EntryIngredient> = buildList {
        recipe.inputs.forEach { add(EntryIngredients.ofIngredient(Ingredient.ofStacks(it))) }
    }.toMutableList()

    override fun getOutputEntries(): MutableList<EntryIngredient> = buildList {
        add(EntryIngredients.ofIngredient(Ingredient.ofStacks(recipe.output)))
    }.toMutableList()

}
