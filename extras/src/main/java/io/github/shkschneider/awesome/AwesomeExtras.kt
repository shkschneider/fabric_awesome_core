package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.extras.KeepXpGameRule
import io.github.shkschneider.awesome.extras.SleepingHeals

object AwesomeExtras {

    operator fun invoke() {
        KeepXpGameRule()
        if (Awesome.CONFIG.extras.sleepingHeals) {
            SleepingHeals()
        }
    }

}
