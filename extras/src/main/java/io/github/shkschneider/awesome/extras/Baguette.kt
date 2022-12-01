package io.github.shkschneider.awesome.extras

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeItem
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.FoodComponent
import net.minecraft.item.FoodComponents

class Baguette : AwesomeItem(
    AwesomeUtils.identifier("baguette"),
    FabricItemSettings()
        .group(Awesome.GROUP)
        .food(FoodComponent.Builder()
            .hunger(FoodComponents.BREAD.hunger * 3)
            .saturationModifier(FoodComponents.BREAD.saturationModifier * 3)
            .build()
        )
)
