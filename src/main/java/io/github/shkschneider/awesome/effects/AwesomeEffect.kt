package io.github.shkschneider.awesome.effects

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.util.registry.Registry

sealed class AwesomeEffect(
    open val id: String,
    val type: StatusEffectCategory,
) : StatusEffect(type, type.formatting.colorValue ?: 0x000000) {

    class Instant(
        override val id: String,
        category: StatusEffectCategory,
        val effect: (livingEntity: LivingEntity) -> Unit,
    ) : AwesomeEffect(id, category) {

        init {
            Registry.register(Registry.STATUS_EFFECT, id, this)
        }

        override fun applyInstantEffect(source: Entity?, attacker: Entity?, target: LivingEntity, amplifier: Int, proximity: Double) {
            if (target.world.isClient) return super.applyInstantEffect(source, attacker, target, amplifier, proximity)
            effect(target)
            super.applyInstantEffect(source, attacker, target, amplifier, proximity)
        }

        override fun isInstant(): Boolean {
            return true
        }

    }

    class Continuous(
        override val id: String,
        category: StatusEffectCategory,
        val effect: (livingEntity: LivingEntity, amplifier: Int) -> Unit,
    ) : AwesomeEffect(id, category) {

        init {
            Registry.register(Registry.STATUS_EFFECT, id, this)
        }

        override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
            if (entity.world.isClient) return super.applyUpdateEffect(entity, amplifier)
            effect(entity, amplifier)
            super.applyUpdateEffect(entity, amplifier)
        }

        override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
            return true
        }

        override fun isInstant(): Boolean {
            return false
        }

    }

}
