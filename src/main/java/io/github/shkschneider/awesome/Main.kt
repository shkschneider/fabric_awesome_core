package io.github.shkschneider.awesome

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

class Main : ModInitializer {

    private val logger = LoggerFactory.getLogger("awesome")

    override fun onInitialize() {
        logger.debug("Awesome!")
    }

}