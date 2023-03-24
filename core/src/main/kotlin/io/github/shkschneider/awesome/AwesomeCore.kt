package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.core.AwesomeTime
import io.github.shkschneider.awesome.custom.Travels

object AwesomeCore {

    operator fun invoke() {
        Awesome()
        AwesomeTime()
        if (Awesome.CONFIG.travels) Travels()
    }

}
