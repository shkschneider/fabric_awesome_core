package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.blocks.AwesomeBlocks
import io.github.shkschneider.awesome.items.AwesomeItems
import io.github.shkschneider.awesome.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.recipes.AwesomeRecipeType
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.Recipe

object SmelterRecipes {

    private val SMELTING = AwesomeRecipeType<Recipe<SmelterBlock.Entity>>()

    operator fun invoke(): List<AwesomeRecipe<SmelterBlock.Entity>> = mutableListOf(
        // Dust -> Ingot/Gem (no redstone)
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Coal.dust, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.COAL, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Copper.dust, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.COPPER_INGOT, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Diamond.dust, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.DIAMOND, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Emerald.dust, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.EMERALD, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Gold.dust, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.GOLD_INGOT, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Iron.dust, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.IRON_INGOT, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Lapis.dust, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.LAPIS_LAZULI, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Quartz.dust, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.QUARTZ, 1)),
        // Raw -> Ingot
        AwesomeRecipe(SMELTING, listOf(ItemStack(Items.RAW_COPPER, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.COPPER_INGOT, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(Items.RAW_GOLD, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.GOLD_INGOT, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(Items.RAW_IRON, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.IRON_INGOT, 1)),
        // RawBlock -> Block
        AwesomeRecipe(SMELTING, listOf(ItemStack(Items.RAW_COPPER_BLOCK, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.COPPER_BLOCK, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(Items.RAW_GOLD_BLOCK, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.GOLD_BLOCK, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(Items.RAW_IRON_BLOCK, 1)), AwesomeItems.Redstone.flux, ItemStack(Items.IRON_BLOCK, 1)),
    ).apply {
        if (Awesome.CONFIG.redstoneFluxFromRandomiumOre) {
            add(AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeBlocks.Randomium.deepslateOre, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Redstone.flux, 1)))
            add(AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeBlocks.Randomium.endOre, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Redstone.flux, 1)))
            add(AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeBlocks.Randomium.ore, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Redstone.flux, 1)))
        }
    }

}
