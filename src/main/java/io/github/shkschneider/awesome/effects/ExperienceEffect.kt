package io.github.shkschneider.awesome.effects

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity

class ExperienceEffect(
    val levels: Int,
) : AwesomeEffect.Instant("experience${levels}", StatusEffectCategory.BENEFICIAL) {

    override fun invoke(source: Entity?, attacker: Entity?, target: LivingEntity, amplifier: Int, proximity: Double) {
        if (target is PlayerEntity && target.isAlive) {
            target.addExperienceLevels(levels)
        }
    }

}
