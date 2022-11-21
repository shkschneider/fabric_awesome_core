package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.AwesomeItems
import io.github.shkschneider.awesome.core.AwesomeRecipe
import io.github.shkschneider.awesome.core.AwesomeRecipeType
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

object CrusherRecipes {

    private val CRUSHING = AwesomeRecipeType<AwesomeRecipe<CrusherBlock.Entity>>()

    operator fun invoke(): List<AwesomeRecipe<CrusherBlock.Entity>> = mutableListOf(
        // Ingot/Gem -> Dust
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.COAL, 1)), time = 20, ItemStack(AwesomeItems.Coal.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.COPPER_INGOT, 1)), time = 20, ItemStack(AwesomeItems.Copper.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.DIAMOND, 1)), time = 20, ItemStack(AwesomeItems.Diamond.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.EMERALD, 1)), time = 20, ItemStack(AwesomeItems.Emerald.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.GOLD_INGOT, 1)), time = 20, ItemStack(AwesomeItems.Gold.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.IRON_INGOT, 1)), time = 20, ItemStack(AwesomeItems.Iron.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.LAPIS_LAZULI, 1)), time = 20, ItemStack(AwesomeItems.Lapis.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.QUARTZ, 1)), time = 20, ItemStack(AwesomeItems.Quartz.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Bronze.ingot, 1)), time = 20, ItemStack(AwesomeItems.Bronze.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Electrum.ingot, 1)), time = 20, ItemStack(AwesomeItems.Electrum.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Lead.ingot, 1)), time = 20, ItemStack(AwesomeItems.Lead.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Nickel.ingot, 1)), time = 20, ItemStack(AwesomeItems.Nickel.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Steel.ingot, 1)), time = 20, ItemStack(AwesomeItems.Steel.dust, 1)),
        // Raw -> Powder
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.RAW_COPPER, 1)), time = 20, ItemStack(AwesomeItems.Copper.powder, 4)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.RAW_GOLD, 1)), time = 20, ItemStack(AwesomeItems.Gold.powder, 4)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.RAW_IRON, 1)), time = 20, ItemStack(AwesomeItems.Iron.powder, 4)),
        // Powder -> Dust
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Coal.powder, 2)), time = 20, ItemStack(AwesomeItems.Coal.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Copper.powder, 2)), time = 20, ItemStack(AwesomeItems.Copper.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Diamond.powder, 2)), time = 20, ItemStack(AwesomeItems.Diamond.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Emerald.powder, 2)), time = 20, ItemStack(AwesomeItems.Emerald.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Gold.powder, 2)), time = 20, ItemStack(AwesomeItems.Gold.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Iron.powder, 2)), time = 20, ItemStack(AwesomeItems.Iron.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Lapis.powder, 2)), time = 20, ItemStack(AwesomeItems.Lapis.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Quartz.powder, 2)), time = 20, ItemStack(AwesomeItems.Quartz.dust, 1)),
        AwesomeRecipe(CRUSHING, listOf(ItemStack(AwesomeItems.Redstone.powder, 2)), time = 20, ItemStack(AwesomeItems.Redstone.dust, 1)),
    ).apply {
        if (Awesome.CONFIG.recipes.diamondDustFromCrushingCoalBlock) {
            add(AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.COAL_BLOCK, 1)), time = 20, ItemStack(AwesomeItems.Diamond.dust, 1)))
        }
        if (Awesome.CONFIG.recipes.redstoneFromCrushingNetherrack) {
            add(AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.NETHERRACK, 64)), time = 20, ItemStack(AwesomeItems.Redstone.dust, 9)))
        }
        if (Awesome.CONFIG.recipes.gravelFromCobblestone) {
            add(AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.COBBLESTONE, 1)), time = 20, ItemStack(Items.GRAVEL, 1)))
        }
        if (Awesome.CONFIG.recipes.sandFromGravel) {
            add(AwesomeRecipe(CRUSHING, listOf(ItemStack(Items.GRAVEL, 1)), time = 20, ItemStack(Items.SAND, 1)))
        }
    }

}
