package io.github.shkschneider.awesome.worldgen

import io.github.shkschneider.awesome.AwesomeConfig

object AwesomeWorldGen {

    operator fun invoke() {
        if (AwesomeConfig.randomiumWorldGen) {
            RandomiumWorldGen()
        }
    }

}
