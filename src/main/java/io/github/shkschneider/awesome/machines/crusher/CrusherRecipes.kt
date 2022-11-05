package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipeType
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.Recipe

object CrusherRecipes {

    private val CRUSHING = AwesomeRecipeType<Recipe<CrusherBlock.Entity>>()

    operator fun invoke(): List<AwesomeRecipe<CrusherBlock.Entity>> = mutableListOf(
        // Ingot/Gem -> Dust
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.COAL, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.coalDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.COPPER_INGOT, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.copperDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.DIAMOND, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.diamondDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.EMERALD, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.emeraldDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.GOLD_INGOT, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.goldDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.IRON_INGOT, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.ironDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.LAPIS_LAZULI, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.lapisDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.QUARTZ, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.quartzDust, 1)),
        // Raw -> Powder
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.RAW_COPPER, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.copperPowder, 4)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.RAW_GOLD, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.goldPowder, 4)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.RAW_IRON, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.ironPowder, 4)),
        // Chip -> Powder
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.coalChip, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.coalPowder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.copperChip, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.copperPowder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.diamondChip, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.diamondPowder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.emeraldChip, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.emeraldPowder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.goldChip, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.goldPowder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.ironChip, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.ironPowder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.lapisChip, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.lapisPowder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.quartzChip, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.quartzPowder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.redstoneChip, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.redstonePowder, 1)),
        // Powder -> Dust
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.coalPowder, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.coalDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.copperPowder, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.copperDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.diamondPowder, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.diamondDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.emeraldPowder, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.emeraldDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.goldPowder, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.goldDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.ironPowder, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.ironDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.lapisPowder, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.lapisDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.quartzPowder, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.quartzDust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.redstonePowder, 2)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.redstoneDust, 1)),
    ).apply {
        if (Awesome.CONFIG.diamondDustFromCrushingCoalBlock) {
            add(AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.COAL_BLOCK, 1)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.diamondDust, 1)))
        }
        if (Awesome.CONFIG.redstoneFromCrushingNetherrack) {
            add(AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.NETHERRACK, 64)), AwesomeMaterials.redstoneFlux, ItemStack(AwesomeMaterials.redstoneDust, 9)))
        }
    }

}
