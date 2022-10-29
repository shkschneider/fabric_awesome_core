package io.github.shkschneider.awesome.events

import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents

// net.fabricmc.fabric.api.event.EventFactory#createArrayBacked()
object AwesomeEvents {

    operator fun invoke() {
        PlayerBlockBreakEvents.AFTER.register(Magnetism())
        PlayerBlockBreakEvents.AFTER.register(OreExperience())
        ServerPlayerEvents.AFTER_RESPAWN.register(PlayerAfterRespawnEvent())
        EntitySleepEvents.STOP_SLEEPING.register(PlayerStopSleepingEvent())
        PlayerBlockBreakEvents.AFTER.register(VeinMining())
    }

}