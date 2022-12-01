package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.AwesomeBlocks
import io.github.shkschneider.awesome.AwesomeItems
import io.github.shkschneider.awesome.core.AwesomeRecipe
import io.github.shkschneider.awesome.core.AwesomeRecipeType
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

object SmelterRecipes {

    private val SMELTING = AwesomeRecipeType<AwesomeRecipe<SmelterBlock.Entity>>()

    operator fun invoke(): List<AwesomeRecipe<SmelterBlock.Entity>> = mutableListOf(
        // Dust -> Ingot/Gem (no redstone)
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Coal.dust, 1)), time = 20, ItemStack(Items.COAL, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Copper.dust, 1)), time = 20, ItemStack(Items.COPPER_INGOT, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Diamond.dust, 1)), time = 20, ItemStack(Items.DIAMOND, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Emerald.dust, 1)), time = 20, ItemStack(Items.EMERALD, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Gold.dust, 1)), time = 20, ItemStack(Items.GOLD_INGOT, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Iron.dust, 1)), time = 20, ItemStack(Items.IRON_INGOT, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Lapis.dust, 1)), time = 20, ItemStack(Items.LAPIS_LAZULI, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Quartz.dust, 1)), time = 20, ItemStack(Items.QUARTZ, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Bronze.dust, 1)), time = 20, ItemStack(AwesomeItems.Bronze.ingot, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Electrum.dust, 1)), time = 20, ItemStack(AwesomeItems.Electrum.ingot, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Lead.dust, 1)), time = 20, ItemStack(AwesomeItems.Lead.ingot, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Nickel.dust, 1)), time = 20, ItemStack(AwesomeItems.Nickel.ingot, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeItems.Steel.dust, 1)), time = 20, ItemStack(AwesomeItems.Steel.ingot, 1)),
        // Raw -> Ingot
        AwesomeRecipe(SMELTING, listOf(ItemStack(Items.RAW_COPPER, 1)), time = 20, ItemStack(Items.COPPER_INGOT, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(Items.RAW_GOLD, 1)), time = 20, ItemStack(Items.GOLD_INGOT, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(Items.RAW_IRON, 1)), time = 20, ItemStack(Items.IRON_INGOT, 1)),
        // RawBlock -> Block
        AwesomeRecipe(SMELTING, listOf(ItemStack(Items.RAW_COPPER_BLOCK, 1)), time = 20, ItemStack(Items.COPPER_BLOCK, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(Items.RAW_GOLD_BLOCK, 1)), time = 20, ItemStack(Items.GOLD_BLOCK, 1)),
        AwesomeRecipe(SMELTING, listOf(ItemStack(Items.RAW_IRON_BLOCK, 1)), time = 20, ItemStack(Items.IRON_BLOCK, 1)),
    ).apply {
        if (Awesome.CONFIG.machines.redstoneFluxFromRandomiumOre) {
            add(AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeBlocks.Randomium.deepslateOre, 1)), time = 20, ItemStack(AwesomeItems.Redstone.flux, 1)))
            add(AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeBlocks.Randomium.endOre, 1)), time = 20, ItemStack(AwesomeItems.Redstone.flux, 1)))
            add(AwesomeRecipe(SMELTING, listOf(ItemStack(AwesomeBlocks.Randomium.ore, 1)), time = 20, ItemStack(AwesomeItems.Redstone.flux, 1)))
        }
    }

}
