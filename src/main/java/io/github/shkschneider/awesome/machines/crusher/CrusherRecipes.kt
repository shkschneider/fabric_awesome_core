package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.items.AwesomeItems
import io.github.shkschneider.awesome.recipes.AwesomeRecipe
import io.github.shkschneider.awesome.recipes.AwesomeRecipeType
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.recipe.Recipe

object CrusherRecipes {

    private val CRUSHING = AwesomeRecipeType<Recipe<CrusherBlock.Entity>>()

    operator fun invoke(): List<AwesomeRecipe<CrusherBlock.Entity>> = mutableListOf(
        // Ingot/Gem -> Dust
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.COAL, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Coal.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.COPPER_INGOT, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Copper.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.DIAMOND, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Diamond.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.EMERALD, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Emerald.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.GOLD_INGOT, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Gold.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.IRON_INGOT, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Iron.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.LAPIS_LAZULI, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Lapis.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.QUARTZ, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Quartz.dust, 1)),
        // Raw -> Powder
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.RAW_COPPER, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Copper.powder, 4)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.RAW_GOLD, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Gold.powder, 4)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.RAW_IRON, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Iron.powder, 4)),
        // Chip -> Powder
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Coal.chip, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Coal.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Copper.chip, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Copper.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Diamond.chip, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Diamond.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Emerald.chip, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Emerald.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Gold.chip, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Gold.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Iron.chip, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Iron.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Lapis.chip, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Lapis.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Quartz.chip, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Quartz.powder, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Redstone.chip, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Redstone.powder, 1)),
        // Powder -> Dust
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Coal.powder, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Coal.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Copper.powder, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Copper.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Diamond.powder, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Diamond.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Emerald.powder, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Emerald.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Gold.powder, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Gold.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Iron.powder, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Iron.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Lapis.powder, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Lapis.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Quartz.powder, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Quartz.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Redstone.powder, 2)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Redstone.dust, 1)),
    ).apply {
        if (Awesome.CONFIG.diamondDustFromCrushingCoalBlock) {
            add(AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.COAL_BLOCK, 1)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Diamond.dust, 1)))
        }
        if (Awesome.CONFIG.redstoneFromCrushingNetherrack) {
            add(AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.NETHERRACK, 64)), AwesomeItems.Redstone.flux, ItemStack(AwesomeItems.Redstone.dust, 9)))
        }
    }

}
