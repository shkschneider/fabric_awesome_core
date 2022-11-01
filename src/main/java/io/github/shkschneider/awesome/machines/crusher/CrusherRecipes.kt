package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.AwesomeConfig
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipeType
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.Recipe

object CrusherRecipes {

    val CRUSHING = AwesomeRecipeType<Recipe<CrusherBlock.Entity>>()

    private val RECIPES = listOf(
        // Ingot/Gem -> Dust
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.COAL, 1)), ItemStack(AwesomeMaterials.coalDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.COPPER_INGOT, 1)), ItemStack(AwesomeMaterials.copperDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.DIAMOND, 1)), ItemStack(AwesomeMaterials.diamondDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.EMERALD, 1)), ItemStack(AwesomeMaterials.emeraldDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.GOLD_INGOT, 1)), ItemStack(AwesomeMaterials.goldDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.IRON_INGOT, 1)), ItemStack(AwesomeMaterials.ironDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.LAPIS_LAZULI, 1)), ItemStack(AwesomeMaterials.lapisDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.QUARTZ, 1)), ItemStack(AwesomeMaterials.quartzDust, 1)),
        // Raw -> Powder
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.RAW_COPPER, 1)), ItemStack(AwesomeMaterials.copperPowder, 4)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.RAW_GOLD, 1)), ItemStack(AwesomeMaterials.goldPowder, 4)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.RAW_IRON, 1)), ItemStack(AwesomeMaterials.ironPowder, 4)),
        // Powder -> Dust
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(AwesomeMaterials.coalPowder, 2)), ItemStack(AwesomeMaterials.coalDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(AwesomeMaterials.copperPowder, 2)), ItemStack(AwesomeMaterials.copperDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(AwesomeMaterials.diamondPowder, 2)), ItemStack(AwesomeMaterials.diamondDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(AwesomeMaterials.emeraldPowder, 2)), ItemStack(AwesomeMaterials.emeraldDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(AwesomeMaterials.goldPowder, 2)), ItemStack(AwesomeMaterials.goldDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(AwesomeMaterials.ironPowder, 2)), ItemStack(AwesomeMaterials.ironDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(AwesomeMaterials.lapisPowder, 2)), ItemStack(AwesomeMaterials.lapisDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(AwesomeMaterials.quartzPowder, 2)), ItemStack(AwesomeMaterials.quartzDust, 1)),
        AwesomeRecipe(CRUSHING, null, listOf(ItemStack(AwesomeMaterials.redstonePowder, 2)), ItemStack(AwesomeMaterials.redstoneDust, 1)),
    )

    operator fun invoke() = RECIPES.toMutableList().apply {
        if (AwesomeConfig.diamondDustFromCrushingCoalBlock) {
            add(AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.COAL_BLOCK, 1)), ItemStack(AwesomeMaterials.diamondDust, 1)))
        }
        if (AwesomeConfig.redstoneFromCrushingNetherrack) {
            add(AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.NETHERRACK, 64)), ItemStack(AwesomeMaterials.redstoneDust, 9)))
        }
    }

}