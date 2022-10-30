package io.github.shkschneider.awesome.events

import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents

object AwesomeEvents {

    operator fun invoke() {
        EntitySleepEvents.STOP_SLEEPING.register(PlayerStopSleepingEvent())
        ServerPlayerEvents.AFTER_RESPAWN.register(PlayerAfterRespawnEvent())
    }

}