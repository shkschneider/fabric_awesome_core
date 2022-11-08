package io.github.shkschneider.awesome.worldgen

import io.github.shkschneider.awesome.Awesome

object AwesomeWorldGen {

    operator fun invoke() {
        if (Awesome.CONFIG.randomiumOre) {
            RandomiumWorldGen()
        }
    }

}
