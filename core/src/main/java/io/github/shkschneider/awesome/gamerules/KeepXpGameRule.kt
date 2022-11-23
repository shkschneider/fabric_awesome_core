package io.github.shkschneider.awesome.gamerules

import io.github.shkschneider.awesome.core.AwesomeRegistries
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.world.GameRules

object KeepXpGameRule {

    operator fun invoke() = AwesomeRegistries.gameRule("keepXp", GameRules.Category.PLAYER, true)

    operator fun invoke(oldPlayer: ServerPlayerEntity, newPlayer: ServerPlayerEntity) {
        newPlayer.experienceLevel = oldPlayer.experienceLevel
        newPlayer.totalExperience = oldPlayer.totalExperience
        newPlayer.experienceProgress = oldPlayer.experienceProgress
    }

}
