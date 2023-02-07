package io.github.shkschneider.awesome.recipes

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.AwesomeCrystals
import io.github.shkschneider.awesome.core.ext.id
import io.github.shkschneider.awesome.crystals.AwesomeCrystal
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
import net.minecraft.item.ItemStack
import net.minecraft.recipe.Ingredient
import net.minecraft.text.Text

@Environment(EnvType.CLIENT)
class AwesomeReiPlugin : REIClientPlugin {

    private val coal: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeCrystals.coal.budding.asItem().id())
    private val copper: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeCrystals.copper.budding.asItem().id())
    private val diamond: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeCrystals.diamond.budding.asItem().id())
    private val emerald: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeCrystals.emerald.budding.asItem().id())
    private val gold: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeCrystals.gold.budding.asItem().id())
    private val iron: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeCrystals.iron.budding.asItem().id())
    private val lapis: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeCrystals.lapis.budding.asItem().id())
    private val netherite: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeCrystals.netherite.budding.asItem().id())
    private val quartz: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeCrystals.quartz.budding.asItem().id())
    private val redstone: CategoryIdentifier<AwesomeReiDisplay> = CategoryIdentifier.of(AwesomeCrystals.redstone.budding.asItem().id())

    override fun registerCategories(registry: CategoryRegistry) {
        registry.add(AwesomeReiCategory(AwesomeCrystals.coal))
        registry.add(AwesomeReiCategory(AwesomeCrystals.copper))
        registry.add(AwesomeReiCategory(AwesomeCrystals.diamond))
        registry.add(AwesomeReiCategory(AwesomeCrystals.emerald))
        registry.add(AwesomeReiCategory(AwesomeCrystals.gold))
        registry.add(AwesomeReiCategory(AwesomeCrystals.iron))
        registry.add(AwesomeReiCategory(AwesomeCrystals.lapis))
        registry.add(AwesomeReiCategory(AwesomeCrystals.netherite))
        registry.add(AwesomeReiCategory(AwesomeCrystals.quartz))
        registry.add(AwesomeReiCategory(AwesomeCrystals.redstone))
    }

    override fun registerDisplays(registry: DisplayRegistry) {
        registry.add(AwesomeReiDisplay(coal, AwesomeCrystals.coal))
        registry.add(AwesomeReiDisplay(copper, AwesomeCrystals.copper))
        registry.add(AwesomeReiDisplay(diamond, AwesomeCrystals.diamond))
        registry.add(AwesomeReiDisplay(emerald, AwesomeCrystals.emerald))
        registry.add(AwesomeReiDisplay(gold, AwesomeCrystals.gold))
        registry.add(AwesomeReiDisplay(iron, AwesomeCrystals.iron))
        registry.add(AwesomeReiDisplay(lapis, AwesomeCrystals.lapis))
        registry.add(AwesomeReiDisplay(netherite, AwesomeCrystals.netherite))
        registry.add(AwesomeReiDisplay(quartz, AwesomeCrystals.quartz))
        registry.add(AwesomeReiDisplay(redstone, AwesomeCrystals.redstone))
    }

}

class AwesomeReiCategory(
    private val crystal: AwesomeCrystal,
) : DisplayCategory<AwesomeReiDisplay> {

    override fun getCategoryIdentifier(): CategoryIdentifier<out AwesomeReiDisplay> =
        CategoryIdentifier.of(Awesome.ID, crystal.id)

    override fun getTitle(): Text = Text.translatable(crystal.budding.translationKey)

    override fun getIcon(): Renderer = EntryStacks.of(crystal.budding.asItem())

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
        18 / 2 + 18 * 1 + 18 / 2

}

class AwesomeReiDisplay(
    private val category: CategoryIdentifier<*>,
    private val crystal: AwesomeCrystal,
) : BasicDisplay(
    EntryIngredients.ofIngredients(mutableListOf(Ingredient.ofStacks(ItemStack(crystal.budding)))),
    mutableListOf(EntryIngredients.of(crystal.output)),
) {

    override fun getCategoryIdentifier(): CategoryIdentifier<*> =
        category

    override fun getInputEntries(): MutableList<EntryIngredient> = mutableListOf(
        EntryIngredients.ofIngredient(Ingredient.ofStacks(ItemStack(crystal.budding)))
    )

    override fun getOutputEntries(): MutableList<EntryIngredient> = mutableListOf(
        EntryIngredients.ofIngredient(Ingredient.ofStacks(crystal.output))
    )

}
