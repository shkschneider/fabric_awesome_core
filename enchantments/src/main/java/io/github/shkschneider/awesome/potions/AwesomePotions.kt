package io.github.shkschneider.awesome.potions

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomePotion
import io.github.shkschneider.awesome.effects.AwesomeEffects
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.Items

object AwesomePotions {

    operator fun invoke() {
        if (Awesome.CONFIG.enchantments.experience) {
            AwesomePotion("experience1", Items.LAPIS_LAZULI, StatusEffectInstance(AwesomeEffects.experience1))
            AwesomePotion("experience9", Items.LAPIS_BLOCK, StatusEffectInstance(AwesomeEffects.experience9))
        }
    }

}
