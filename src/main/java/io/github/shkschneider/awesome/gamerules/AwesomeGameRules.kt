package io.github.shkschneider.awesome.gamerules

import io.github.shkschneider.awesome.AwesomeConfig
import net.minecraft.world.GameRules
import net.minecraft.world.GameRules.BooleanRule

object AwesomeGameRules {

    lateinit var keepXp: GameRules.Key<BooleanRule>
    lateinit var oreXp: GameRules.Key<BooleanRule>
    lateinit var pvp: GameRules.Key<BooleanRule>
    lateinit var sleepingHeals: GameRules.Key<BooleanRule>

    operator fun invoke() {
        keepXp = KeepXpGameRule()
        if (AwesomeConfig.oreDropXpWithExperienceEnchantment) {
            oreXp = OreXpGameRule()
        }
        pvp = PvpGameRule()
        sleepingHeals = SleepingHealsGameRule()
    }

    // operator fun invoke(world: World, key: GameRules.Key<BooleanRule>) = world.gameRules.getBoolean(key)

}
