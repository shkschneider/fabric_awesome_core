package io.github.shkschneider.awesome.potions

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomePotion
import io.github.shkschneider.awesome.effects.AwesomeEffects
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.Items

object AwesomePotions {

    private lateinit var EXPERIENCE_1: AwesomePotion
    private lateinit var EXPERIENCE_9: AwesomePotion

    init {
        if (Awesome.CONFIG.enchantments.experience) {
            EXPERIENCE_1 = AwesomePotion("experience1", Items.LAPIS_LAZULI, StatusEffectInstance(AwesomeEffects.experience1))
            EXPERIENCE_9 = AwesomePotion("experience9", Items.LAPIS_BLOCK, StatusEffectInstance(AwesomeEffects.experience9))
        }
    }

    operator fun invoke(): List<AwesomePotion> = buildList {
        if (Awesome.CONFIG.enchantments.experience) {
            add(EXPERIENCE_1)
            add(EXPERIENCE_9)
        }
    }

}
