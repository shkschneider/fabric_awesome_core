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

    // Vanilla

    val copperDustFromIngot = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.COPPER_INGOT, 1)), ItemStack(AwesomeMaterials.copperDust, 1))
    val diamondDustFromGem = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.DIAMOND, 1)), ItemStack(AwesomeMaterials.diamondDust, 1))
    val emeraldDustFromGem = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.EMERALD, 1)), ItemStack(AwesomeMaterials.emeraldDust, 1))
    val goldDustFromIngot = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.GOLD_INGOT, 1)), ItemStack(AwesomeMaterials.goldDust, 1))
    val ironDustFromIngot = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.IRON_INGOT, 1)), ItemStack(AwesomeMaterials.ironDust, 1))
    val lapisDustFromGem = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.LAPIS_LAZULI, 1)), ItemStack(AwesomeMaterials.lapisDust, 1))

    // Awesome

    val crushedCopperFromRaw = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.RAW_COPPER, 1)), ItemStack(AwesomeMaterials.crushedCopper, 4))
    val crushedGoldFromRaw = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.RAW_GOLD, 1)), ItemStack(AwesomeMaterials.crushedGold, 4))
    val crushedIronFromRaw = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.RAW_IRON, 1)), ItemStack(AwesomeMaterials.crushedIron, 4))

    val copperDustFromCrushed = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(AwesomeMaterials.crushedCopper, 2)), ItemStack(AwesomeMaterials.copperDust, 1))
    val goldDustFromCrushed = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(AwesomeMaterials.crushedGold, 2)), ItemStack(AwesomeMaterials.goldDust, 1))
    val ironDustFromCrushed = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(AwesomeMaterials.crushedIron, 2)), ItemStack(AwesomeMaterials.ironDust, 1))

    val diamondDustFromCoalBlock = AwesomeRecipe(CRUSHING, null, listOf(ItemStack(Items.COAL_BLOCK, 1)), ItemStack(AwesomeMaterials.diamondDust, 1))

    operator fun invoke(): List<AwesomeRecipe<CrusherBlock.Entity>> = mutableListOf(
        // Vanilla
        copperDustFromIngot,
        diamondDustFromGem,
        emeraldDustFromGem,
        goldDustFromIngot,
        ironDustFromIngot,
        lapisDustFromGem,
        // Awesome
        crushedCopperFromRaw,
        crushedGoldFromRaw,
        crushedIronFromRaw,
        copperDustFromCrushed,
        goldDustFromCrushed,
        ironDustFromCrushed,
    ).apply {
        if (AwesomeConfig.diamondDustFromCrushingCoalBlock) add(diamondDustFromCoalBlock)
    }

}