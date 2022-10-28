package io.github.shkschneider.awesome.effects

import io.github.shkschneider.awesome.effects.potions.AwesomePotions
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity

object AwesomeEffects {

    val experience1 = AwesomeEffect.Instant("experience1", StatusEffectCategory.BENEFICIAL) { livingEntity ->
        if (livingEntity is PlayerEntity) {
            livingEntity.addExperienceLevels(1)
        }
    }
    val experience9 = AwesomeEffect.Instant("experience9", StatusEffectCategory.BENEFICIAL) { livingEntity ->
        if (livingEntity is PlayerEntity) {
            livingEntity.addExperienceLevels(9)
        }
    }

    operator fun invoke() {
        AwesomePotions()
    }

}
