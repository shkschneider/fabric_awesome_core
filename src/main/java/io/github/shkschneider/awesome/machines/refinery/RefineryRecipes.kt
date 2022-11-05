package io.github.shkschneider.awesome.machines.refinery

import io.github.shkschneider.awesome.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.recipes.AwesomeRecipeType
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.Recipe

object RefineryRecipes {

    private val REFINING = AwesomeRecipeType<Recipe<RefineryBlock.Entity>>()

    operator fun invoke(): List<AwesomeRecipe<RefineryBlock.Entity>> = mutableListOf(
        // Ore -> Chip
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.COAL_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.coalChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.COPPER_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.copperChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.DEEPSLATE_COAL_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.coalChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.DEEPSLATE_COPPER_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.copperChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.DEEPSLATE_DIAMOND_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.diamondChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.DEEPSLATE_EMERALD_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.emeraldChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.DEEPSLATE_GOLD_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.goldChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.DEEPSLATE_IRON_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.ironChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.DEEPSLATE_LAPIS_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.lapisChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.DEEPSLATE_REDSTONE_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.redstoneChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.DIAMOND_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.diamondChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.EMERALD_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.emeraldChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.GOLD_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.goldChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.IRON_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.ironChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.LAPIS_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.lapisChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.NETHER_GOLD_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.goldChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.NETHER_QUARTZ_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.quartzChip, 16)),
        AwesomeRecipe(REFINING, listOf(ItemStack(Items.REDSTONE_ORE, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.redstoneChip, 16)),
    )

}
