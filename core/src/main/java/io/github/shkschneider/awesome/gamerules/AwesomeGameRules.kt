package io.github.shkschneider.awesome.gamerules

import net.minecraft.world.GameRules

object AwesomeGameRules {

    private lateinit var KEEP_XP: GameRules.Key<GameRules.BooleanRule>
    val keepXp get() = KEEP_XP

    operator fun invoke() {
        KEEP_XP = KeepXpGameRule()
    }

}
