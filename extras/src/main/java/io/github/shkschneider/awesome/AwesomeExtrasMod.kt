package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.extras.KeepXpGameRule
import net.fabricmc.api.ModInitializer

class AwesomeExtrasMod : ModInitializer {

    override fun onInitialize() {
        KeepXpGameRule()
    }

}
