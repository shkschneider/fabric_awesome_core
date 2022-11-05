package io.github.shkschneider.awesome.gamerules

import io.github.shkschneider.awesome.core.AwesomeRegistries
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.GameRules

object SleepingHealsGameRule {

    operator fun invoke() = AwesomeRegistries.gameRule("sleepingHeals", GameRules.Category.PLAYER, true)

    init {
        EntitySleepEvents.STOP_SLEEPING.register(EntitySleepEvents.StopSleeping { entity: LivingEntity, _: BlockPos ->
            if (entity.world.gameRules.getBoolean(AwesomeGameRules.sleepingHeals)) {
                invoke(entity)
            }
        })
    }

    operator fun invoke(entity: LivingEntity) {
        entity.health = entity.maxHealth
    }

}
