package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.core.AwesomeTime
import io.github.shkschneider.awesome.core.Dimensions
import io.github.shkschneider.awesome.gamerules.AwesomeGameRules
import net.fabricmc.api.ModInitializer

class AwesomeCoreMod : ModInitializer {

    override fun onInitialize() {
        AwesomeGameRules()
        AwesomeTime()
        Dimensions()
    }

}
