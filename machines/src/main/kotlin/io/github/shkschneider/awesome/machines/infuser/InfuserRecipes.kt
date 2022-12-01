package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.core.AwesomeRecipe
import io.github.shkschneider.awesome.core.AwesomeRecipeType
import io.github.shkschneider.awesome.AwesomeItems
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

object InfuserRecipes {

    private val INFUSING = AwesomeRecipeType<AwesomeRecipe<InfuserBlock.Entity>>()

    operator fun invoke(): List<AwesomeRecipe<InfuserBlock.Entity>> = mutableListOf(
        // Powder -> Dust
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Coal.powder, 4), ItemStack(Items.GRAVEL, 1)), time = 20, ItemStack(AwesomeItems.Coal.dust, 3)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Copper.powder, 4), ItemStack(Items.GRAVEL, 1)), time = 20, ItemStack(AwesomeItems.Copper.dust, 3)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Diamond.powder, 4), ItemStack(Items.GRAVEL, 1)), time = 20, ItemStack(AwesomeItems.Diamond.dust, 3)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Emerald.powder, 4), ItemStack(Items.GRAVEL, 1)), time = 20, ItemStack(AwesomeItems.Emerald.dust, 3)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Gold.powder, 4), ItemStack(Items.GRAVEL, 1)), time = 20, ItemStack(AwesomeItems.Gold.dust, 3)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Iron.powder, 4), ItemStack(Items.GRAVEL, 1)), time = 20, ItemStack(AwesomeItems.Iron.dust, 3)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Lapis.powder, 4), ItemStack(Items.GRAVEL, 1)), time = 20, ItemStack(AwesomeItems.Lapis.dust, 3)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Quartz.powder, 4), ItemStack(Items.GRAVEL, 1)), time = 20, ItemStack(AwesomeItems.Quartz.dust, 3)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Redstone.powder, 4), ItemStack(Items.GRAVEL, 1)), time = 20, ItemStack(AwesomeItems.Redstone.dust, 3)),
        // Chip -> Powder
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Coal.chip, 4), ItemStack(Items.SAND, 1)), time = 20, ItemStack(AwesomeItems.Coal.powder, 8)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Copper.chip, 4), ItemStack(Items.SAND, 1)), time = 20, ItemStack(AwesomeItems.Copper.powder, 8)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Diamond.chip, 4), ItemStack(Items.SAND, 1)), time = 20, ItemStack(AwesomeItems.Diamond.powder, 8)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Emerald.chip, 4), ItemStack(Items.SAND, 1)), time = 20, ItemStack(AwesomeItems.Emerald.powder, 8)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Gold.chip, 4), ItemStack(Items.SAND, 1)), time = 20, ItemStack(AwesomeItems.Gold.powder, 8)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Iron.chip, 4), ItemStack(Items.SAND, 1)), time = 20, ItemStack(AwesomeItems.Iron.powder, 8)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Lapis.chip, 4), ItemStack(Items.SAND, 1)), time = 20, ItemStack(AwesomeItems.Lapis.powder, 8)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Quartz.chip, 4), ItemStack(Items.SAND, 1)), time = 20, ItemStack(AwesomeItems.Quartz.powder, 8)),
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Redstone.chip, 4), ItemStack(Items.SAND, 1)), time = 20, ItemStack(AwesomeItems.Redstone.powder, 8)),
        // RedstoneFlux
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Lapis.dust, 1), ItemStack(AwesomeItems.Redstone.dust, 1)), time = 20, ItemStack(AwesomeItems.Redstone.flux, 2)),
        // Bronze
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Copper.dust, 3), ItemStack(AwesomeItems.Lead.dust, 1)), time = 20, ItemStack(AwesomeItems.Bronze.dust, 2)),
        // Electrum
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Iron.dust, 3), ItemStack(AwesomeItems.Gold.dust, 1)), time = 20, ItemStack(AwesomeItems.Electrum.dust, 2)),
        // Lead
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Iron.dust, 1), ItemStack(AwesomeItems.Coal.dust, 1)), time = 20, ItemStack(AwesomeItems.Lead.dust, 2)),
        // Nickel
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Iron.dust, 3), ItemStack(AwesomeItems.Coal.dust, 1)), time = 20, ItemStack(AwesomeItems.Nickel.dust, 2)),
        // Steel
        AwesomeRecipe(INFUSING, listOf(ItemStack(AwesomeItems.Iron.dust, 8), ItemStack(AwesomeItems.Coal.dust, 1)), time = 20, ItemStack(AwesomeItems.Steel.dust, 2)),
    )

}
