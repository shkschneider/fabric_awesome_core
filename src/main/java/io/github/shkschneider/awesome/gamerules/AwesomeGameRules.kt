package io.github.shkschneider.awesome.gamerules

import io.github.shkschneider.awesome.custom.SilkTouchSpawners
import net.minecraft.world.GameRules
import net.minecraft.world.GameRules.BooleanRule
import net.minecraft.world.World

object AwesomeGameRules {

    val keepXp: GameRules.Key<BooleanRule> = KeepXpGameRule()
    val oreXp: GameRules.Key<BooleanRule> = OreXpGameRule()
    val pvp: GameRules.Key<BooleanRule> = PvpGameRule()
    val sleepingHeals: GameRules.Key<BooleanRule> = SleepingHealsGameRule()

    operator fun invoke() {
        SilkTouchSpawners()
    }

    // operator fun invoke(world: World, key: GameRules.Key<BooleanRule>) = world.gameRules.getBoolean(key)

}