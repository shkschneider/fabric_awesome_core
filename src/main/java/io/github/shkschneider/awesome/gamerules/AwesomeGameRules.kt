package io.github.shkschneider.awesome.gamerules

import io.github.shkschneider.awesome.Awesome
import net.minecraft.world.GameRules

object AwesomeGameRules {

    private lateinit var _keepXp: GameRules.Key<GameRules.BooleanRule>
    val keepXp get() = _keepXp
    private lateinit var _oreXp: GameRules.Key<GameRules.BooleanRule>
    val oreXp get() = _oreXp
    private lateinit var _pvp: GameRules.Key<GameRules.BooleanRule>
    val pvp get() = _pvp
    private lateinit var _sleepingHeals: GameRules.Key<GameRules.BooleanRule>
    val sleepingHeals get() = _sleepingHeals

    operator fun invoke() {
        _keepXp = KeepXpGameRule()
        if (Awesome.CONFIG.oreDropXp) {
            _oreXp = OreXpGameRule()
        }
        _pvp = PvpGameRule()
        _sleepingHeals = SleepingHealsGameRule()
    }

}
