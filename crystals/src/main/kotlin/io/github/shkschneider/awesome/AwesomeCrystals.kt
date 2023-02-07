package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.crystals.AwesomeCrystal

object AwesomeCrystals {

    operator fun invoke() {
        if (Awesome.CONFIG.crystals.coal) AwesomeCrystal("coal")
        if (Awesome.CONFIG.crystals.copper) AwesomeCrystal("copper")
        if (Awesome.CONFIG.crystals.diamond) AwesomeCrystal("diamond")
        if (Awesome.CONFIG.crystals.emerald) AwesomeCrystal("emerald")
        if (Awesome.CONFIG.crystals.gold) AwesomeCrystal("gold")
        if (Awesome.CONFIG.crystals.iron) AwesomeCrystal("iron")
        if (Awesome.CONFIG.crystals.lapis) AwesomeCrystal("lapis")
        if (Awesome.CONFIG.crystals.redstone) AwesomeCrystal("redstone")
    }

}
