package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.recipes.AwesomeRecipeType
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.Recipe

object CrusherRecipes {

    private val CRUSHING = AwesomeRecipeType<Recipe<CrusherBlock.Entity>>()

    operator fun invoke(): List<AwesomeRecipe<CrusherBlock.Entity>> = mutableListOf(
        // Ingot/Gem -> Dust
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.COAL, 1)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Coal.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.COPPER_INGOT, 1)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Copper.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.DIAMOND, 1)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Diamond.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.EMERALD, 1)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Emerald.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.GOLD_INGOT, 1)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Gold.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.IRON_INGOT, 1)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Iron.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.LAPIS_LAZULI, 1)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Lapis.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.QUARTZ, 1)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Quartz.dust, 1)),
        // Raw -> Powder
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.RAW_COPPER, 1)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Copper.powder, 4)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.RAW_GOLD, 1)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Gold.powder, 4)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.RAW_IRON, 1)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Iron.powder, 4)),
        // Chip -> Powder
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Coal.chip, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Coal.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Copper.chip, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Copper.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Diamond.chip, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Diamond.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Emerald.chip, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Emerald.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Gold.chip, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Gold.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Iron.chip, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Iron.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Lapis.chip, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Lapis.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Quartz.chip, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Quartz.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Redstone.chip, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Redstone.powder, 1)),
        // Powder -> Dust
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Coal.powder, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Coal.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Copper.powder, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Copper.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Diamond.powder, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Diamond.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Emerald.powder, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Emerald.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Gold.powder, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Gold.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Iron.powder, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Iron.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Lapis.powder, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Lapis.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Quartz.powder, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Quartz.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeMaterials.Redstone.powder, 2)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Redstone.dust, 1)),
    ).apply {
        if (Awesome.CONFIG.diamondDustFromCrushingCoalBlock) {
            add(AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.COAL_BLOCK, 1)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Diamond.dust, 1)))
        }
        if (Awesome.CONFIG.redstoneFromCrushingNetherrack) {
            add(AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.NETHERRACK, 64)), AwesomeMaterials.Redstone.flux, ItemStack(AwesomeMaterials.Redstone.dust, 9)))
        }
    }

}
