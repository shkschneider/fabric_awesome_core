package io.github.shkschneider.awesome

import net.fabricmc.api.ModInitializer

class Awesome : ModInitializer {

    companion object {

        const val ID = "awesome"

    }

    override fun onInitialize() {
        Logger.debug("Awesome!")
        AwesomeItemGroups()
    }

}