package io.github.shkschneider.awesome.events

import io.github.shkschneider.awesome.gamerules.AwesomeGameRules
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos

class PlayerStopSleepingEvent : EntitySleepEvents.StopSleeping {

    override fun onStopSleeping(entity: LivingEntity?, sleepingPos: BlockPos?) {
        if (entity == null) return
        if (AwesomeGameRules.sleepingHeals(entity.world)) {
            entity.health = entity.maxHealth
        }
    }

}