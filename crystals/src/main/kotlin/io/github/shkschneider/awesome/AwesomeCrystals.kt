package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.crystals.AwesomeCrystal

object AwesomeCrystals {

    operator fun invoke() {
        if (Awesome.CONFIG.crystals.lapis) AwesomeCrystal("lapis")
        if (Awesome.CONFIG.crystals.redstone) AwesomeCrystal("redstone")
    }

}
