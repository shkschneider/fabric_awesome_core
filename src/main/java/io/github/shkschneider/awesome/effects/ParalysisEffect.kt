package io.github.shkschneider.awesome.effects

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.util.math.Vec3d

class ParalysisEffect : AwesomeEffect.Continuous("paralysis", StatusEffectCategory.HARMFUL) {

    override fun invoke(entity: LivingEntity, amplifier: Int) {
        if (entity.isAlive) {
            entity.teleport(entity.x, entity.y, entity.z)
            entity.velocity = Vec3d.ZERO
        }
    }

}