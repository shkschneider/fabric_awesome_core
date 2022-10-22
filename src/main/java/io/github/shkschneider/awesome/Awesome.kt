package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.titanium.Titanium
import net.fabricmc.api.ModInitializer

class Awesome : ModInitializer {

    companion object {

        const val ID = "awesome"

    }

    override fun onInitialize() {
        Logger.debug("Awesome!")
        Titanium()
        AwesomeItemGroups()
    }

}