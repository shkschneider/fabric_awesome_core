package io.github.shkschneider.awesome

import net.fabricmc.api.ModInitializer

class AwesomeCore : ModInitializer {

    companion object {

        val ID = "awesome".lowercase()
        val NAME = ID.replaceFirstChar { it.uppercase() }

    }

    override fun onInitialize() = Unit

}
