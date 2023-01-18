package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.extras.Baguette
import io.github.shkschneider.awesome.extras.GameRulesOverrides
import io.github.shkschneider.awesome.extras.KeepXpGameRule
import io.github.shkschneider.awesome.extras.SleepingHeals
import io.github.shkschneider.awesome.extras.ZenithNadirLengths
import io.github.shkschneider.awesome.extras.blocks.AwesomeBlocks
import io.github.shkschneider.awesome.extras.crate.Crate
import io.github.shkschneider.awesome.extras.entities.AwesomeEntities
import io.github.shkschneider.awesome.worldgen.RandomiumWorldGen

object AwesomeExtras {

    operator fun invoke() {
        KeepXpGameRule()
        if (Awesome.CONFIG.extras.baguette) Baguette()
        if (Awesome.CONFIG.extras.blocks) AwesomeBlocks()
        if (Awesome.CONFIG.extras.crate) Crate()
        if (Awesome.CONFIG.extras.entities) AwesomeEntities()
        if (Awesome.CONFIG.extras.gameRulesOverrides) GameRulesOverrides()
        if (Awesome.CONFIG.extras.randomium) RandomiumWorldGen()
        if (Awesome.CONFIG.extras.sleepingHeals) SleepingHeals()
        if (Awesome.CONFIG.extras.zenithLengthInDays + Awesome.CONFIG.extras.nadirLengthInDays > 0F) ZenithNadirLengths()
    }

}
