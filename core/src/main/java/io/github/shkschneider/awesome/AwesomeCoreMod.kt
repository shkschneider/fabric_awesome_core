package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.core.AwesomeTime
import io.github.shkschneider.awesome.core.Dimensions
import net.fabricmc.api.ModInitializer

class AwesomeCoreMod : ModInitializer {

    override fun onInitialize() {
        AwesomeTime()
        Dimensions()
    }

}
