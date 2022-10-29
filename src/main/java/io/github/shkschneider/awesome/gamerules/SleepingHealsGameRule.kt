package io.github.shkschneider.awesome.gamerules

import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.GameRules
import net.minecraft.world.World

object SleepingHealsGameRule {

    operator fun invoke() = GameRuleRegistry.register("sleepingHeals", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true))

    operator fun invoke(world: World) = world.gameRules.getBoolean(AwesomeGameRules.sleepingHeals)

    init {
        EntitySleepEvents.STOP_SLEEPING.register(EntitySleepEvents.StopSleeping { entity: LivingEntity, _: BlockPos ->
            if (invoke(entity.world)) {
                invoke(entity)
            }
        })
    }

    operator fun invoke(entity: LivingEntity) {
        if (invoke(entity.world)) {
            entity.health = entity.maxHealth
        }
    }

}