package io.github.shkschneider.awesome.effects.potions

import io.github.shkschneider.awesome.mixins.BrewingRecipeRegistryMixin
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.Item
import net.minecraft.potion.Potion
import net.minecraft.potion.Potions
import net.minecraft.util.registry.Registry

class AwesomePotion(
    val id: String,
    item: Item,
    effectInstance: StatusEffectInstance,
) {

    val potion = Registry.register(Registry.POTION, id, Potion(effectInstance))

    init {
        BrewingRecipeRegistryMixin.registerPotionRecipe(Potions.AWKWARD, item, potion)
    }

}