package io.github.shkschneider.awesome.extras

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeItem
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.FoodComponent
import net.minecraft.item.FoodComponents
import net.minecraft.text.Text

class Baguette : AwesomeItem(
    AwesomeUtils.identifier(NAME),
    FabricItemSettings()
        .group(Awesome.GROUP)
        .food(FoodComponent.Builder()
            .hunger(FoodComponents.BREAD.hunger * 2)
            .saturationModifier(FoodComponents.BREAD.saturationModifier)
            .build()
        )
) {

    companion object {

        const val NAME = "baguette"

    }

    override fun appendShiftableTooltip(): Text =
        Text.translatable(AwesomeUtils.translatable("item", NAME, "hint"))

}
