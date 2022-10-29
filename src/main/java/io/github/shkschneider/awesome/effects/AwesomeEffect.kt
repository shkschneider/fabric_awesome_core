package io.github.shkschneider.awesome.effects

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.util.registry.Registry

sealed class AwesomeEffect(
    open val id: String,
    type: StatusEffectCategory,
    color: Int = type.formatting.colorValue ?: 0x000000,
) : StatusEffect(type, color) {

    abstract class Instant(
        override val id: String,
        type: StatusEffectCategory,
        color: Int = type.formatting.colorValue ?: 0x000000,
    ) : AwesomeEffect(id, type, color) {

        operator fun invoke() {
            Registry.register(Registry.STATUS_EFFECT, id, this)
        }

        abstract fun invoke(source: Entity?, attacker: Entity?, target: LivingEntity, amplifier: Int, proximity: Double)

        override fun applyInstantEffect(source: Entity?, attacker: Entity?, target: LivingEntity, amplifier: Int, proximity: Double) {
            if (target.world.isClient) return super.applyInstantEffect(source, attacker, target, amplifier, proximity)
            invoke(source, attacker, target, amplifier, proximity)
            super.applyInstantEffect(source, attacker, target, amplifier, proximity)
        }

        override fun isInstant(): Boolean {
            return true
        }

    }

    abstract class Continuous(
        override val id: String,
        type: StatusEffectCategory,
        color: Int = type.formatting.colorValue ?: 0x000000,
    ) : AwesomeEffect(id, type, color) {

        operator fun invoke() {
            Registry.register(Registry.STATUS_EFFECT, id, this)
        }

        override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
            if (entity.world.isClient) return super.applyUpdateEffect(entity, amplifier)
            invoke(entity, amplifier)
            super.applyUpdateEffect(entity, amplifier)
        }

        abstract fun invoke(entity: LivingEntity, amplifier: Int)

        override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
            return true
        }

        override fun isInstant(): Boolean {
            return false
        }

    }

}
