package io.github.shkschneider.awesome.events

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.minecraft.server.network.ServerPlayerEntity

class PlayerAfterRespawnEvent : ServerPlayerEvents.AfterRespawn {

    override fun afterRespawn(oldPlayer: ServerPlayerEntity?, newPlayer: ServerPlayerEntity?, alive: Boolean) {
        if (oldPlayer == null || newPlayer == null) return
        oldPlayer.statusEffects.forEach { effect ->
            newPlayer.addStatusEffect(effect)
        }
    }

}