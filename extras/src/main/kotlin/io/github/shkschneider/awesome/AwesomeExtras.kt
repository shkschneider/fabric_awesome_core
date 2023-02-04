package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.extras.Baguette
import io.github.shkschneider.awesome.extras.GameRulesOverrides
import io.github.shkschneider.awesome.extras.KeepXpGameRule
import io.github.shkschneider.awesome.extras.SleepingHeals
import io.github.shkschneider.awesome.extras.ZenithNadirLengths
import io.github.shkschneider.awesome.extras.budding.BuddingLapisBlock
import io.github.shkschneider.awesome.extras.budding.BuddingRedstoneBlock
import io.github.shkschneider.awesome.extras.crate.Crate
import io.github.shkschneider.awesome.extras.elevator.Elevator
import io.github.shkschneider.awesome.extras.entities.AwesomeEntities
import io.github.shkschneider.awesome.extras.randomium.Randomium
import io.github.shkschneider.awesome.extras.void.Void

object AwesomeExtras {

    operator fun invoke() {
        KeepXpGameRule()
        if (Awesome.CONFIG.extras.baguette) Baguette()
        if (Awesome.CONFIG.extras.budding) BuddingLapisBlock()
        if (Awesome.CONFIG.extras.budding) BuddingRedstoneBlock()
        if (Awesome.CONFIG.extras.crate) Crate()
        if (Awesome.CONFIG.extras.elevator) Elevator()
        if (Awesome.CONFIG.extras.entities) AwesomeEntities()
        if (Awesome.CONFIG.extras.gameRulesOverrides) GameRulesOverrides()
        if (Awesome.CONFIG.extras.randomium) Randomium()
        if (Awesome.CONFIG.extras.sleepingHeals) SleepingHeals()
        if (Awesome.CONFIG.extras.void) Void()
        if (Awesome.CONFIG.extras.zenithLengthInDays + Awesome.CONFIG.extras.nadirLengthInDays > 0F) ZenithNadirLengths()
    }

}
