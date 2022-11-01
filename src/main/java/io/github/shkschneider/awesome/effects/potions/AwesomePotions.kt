package io.github.shkschneider.awesome.effects.potions

import io.github.shkschneider.awesome.AwesomeConfig
import io.github.shkschneider.awesome.effects.AwesomeEffects
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.Items

object AwesomePotions {

    val experience1 = AwesomePotion("experience1", Items.LAPIS_LAZULI, StatusEffectInstance(AwesomeEffects.experience1))
    val experience9 = AwesomePotion("experience9", Items.LAPIS_BLOCK, StatusEffectInstance(AwesomeEffects.experience9))

    operator fun invoke(): List<AwesomePotion> = mutableListOf<AwesomePotion>(
    ).apply {
        if (AwesomeConfig.experiencePotions) {
            add(experience1)
            add(experience9)
        }
    }

}