package io.github.shkschneider.awesome.extras

import io.github.shkschneider.awesome.core.AwesomeRegistries
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.world.GameRules

object KeepXpGameRule {

    private lateinit var KEY: GameRules.Key<GameRules.BooleanRule>
    val key get() = KEY

    operator fun invoke() {
        KEY = AwesomeRegistries.gameRule("keepXp", GameRules.Category.PLAYER, true)
    }

    operator fun invoke(oldPlayer: ServerPlayerEntity, newPlayer: ServerPlayerEntity) {
        newPlayer.experienceLevel = oldPlayer.experienceLevel
        newPlayer.totalExperience = oldPlayer.totalExperience
        newPlayer.experienceProgress = oldPlayer.experienceProgress
    }

}
