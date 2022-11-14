package io.github.shkschneider.awesome.gamerules

import io.github.shkschneider.awesome.core.AwesomeRegistries
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.world.GameRules

object SleepingHealsGameRule {

    operator fun invoke() = AwesomeRegistries.gameRule("sleepingHeals", GameRules.Category.PLAYER, true)

    init {
        EntitySleepEvents.STOP_SLEEPING.register(EntitySleepEvents.StopSleeping { livingEntity, _ ->
            val sleepingHeals = livingEntity.world.gameRules.getBoolean(AwesomeGameRules.sleepingHeals)
            if (sleepingHeals && livingEntity.isPlayer) {
                invoke(livingEntity)
            }
        })
    }

    operator fun invoke(livingEntity: LivingEntity) {
        livingEntity.addStatusEffect(StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 5))
        livingEntity.addStatusEffect(StatusEffectInstance(StatusEffects.WEAKNESS, 5))
    }

}
