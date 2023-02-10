package io.github.shkschneider.awesome.extras.baguette

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeItem
import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.FoodComponent
import net.minecraft.item.FoodComponents
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.world.World

class Baguette : AwesomeItem(
    id = AwesomeUtils.identifier("baguette"),
    settings = FabricItemSettings()
        .food(FoodComponent.Builder()
            .hunger(FoodComponents.BREAD.hunger * 2)
            .saturationModifier(FoodComponents.BREAD.saturationModifier)
            .build()
        ),
    group = Awesome.GROUP,
) {

    init {
        AwesomeRegistries.group(ItemGroups.FOOD_AND_DRINK, this)
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        tooltip.add(Text.translatable(AwesomeUtils.translatable("item", id.path, "hint")).formatted(Formatting.GRAY))
    }

}
