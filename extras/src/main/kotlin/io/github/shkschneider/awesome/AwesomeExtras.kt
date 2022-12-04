package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.extras.Baguette
import io.github.shkschneider.awesome.extras.GameRulesOverrides
import io.github.shkschneider.awesome.extras.KeepXpGameRule
import io.github.shkschneider.awesome.extras.SleepingHeals
import io.github.shkschneider.awesome.extras.ZenithNadirLengths

object AwesomeExtras {

    operator fun invoke() {
        KeepXpGameRule()
        if (Awesome.CONFIG.extras.baguette) Baguette()
        if (Awesome.CONFIG.extras.gameRulesOverrides) GameRulesOverrides()
        if (Awesome.CONFIG.extras.sleepingHeals) SleepingHeals()
        if (Awesome.CONFIG.extras.zenithLengthInDays + Awesome.CONFIG.extras.nadirLengthInDays > 0F) ZenithNadirLengths()
    }

}
