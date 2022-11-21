package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.custom.SilkTouchSpawners
import io.github.shkschneider.awesome.gamerules.KeepXpGameRule
import io.github.shkschneider.awesome.gamerules.OreXpGameRule
import io.github.shkschneider.awesome.gamerules.PvpGameRule
import io.github.shkschneider.awesome.gamerules.SleepingHealsGameRule
import net.minecraft.world.GameRules

object AwesomeGameRules {

    private lateinit var KEEP_XP: GameRules.Key<GameRules.BooleanRule>
    val keepXp get() = KEEP_XP
    private lateinit var ORE_XP: GameRules.Key<GameRules.BooleanRule>
    val oreXp get() = ORE_XP
    private lateinit var PVP: GameRules.Key<GameRules.BooleanRule>
    val pvp get() = PVP
    private lateinit var SLEEPING_HEALS: GameRules.Key<GameRules.BooleanRule>
    val sleepingHeals get() = SLEEPING_HEALS

    operator fun invoke() {
        KEEP_XP = KeepXpGameRule()
        if (Awesome.CONFIG.gameRules.oreXp) {
            ORE_XP = OreXpGameRule()
        }
        PVP = PvpGameRule()
        SLEEPING_HEALS = SleepingHealsGameRule()
        SilkTouchSpawners() // TODO gamerule/config
        // TODO villagers gamerule/config
    }

}
