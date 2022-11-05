package io.github.shkschneider.awesome.potions

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomePotion
import io.github.shkschneider.awesome.effects.AwesomeEffects
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.Items

object AwesomePotions {

    private lateinit var _experience1: AwesomePotion
    val experience1 get() = _experience1
    private lateinit var _experience9: AwesomePotion
    val experience9 get() = _experience9

    init {
        if (Awesome.CONFIG.experiencePotions) {
            _experience1 = AwesomePotion("experience1", Items.LAPIS_LAZULI, StatusEffectInstance(AwesomeEffects.experience1))
            _experience9 = AwesomePotion("experience9", Items.LAPIS_BLOCK, StatusEffectInstance(AwesomeEffects.experience9))
        }
    }

    operator fun invoke(): List<AwesomePotion> = buildList {
        if (Awesome.CONFIG.experiencePotions) {
            add(experience1)
            add(experience9)
        }
    }

}
