package io.github.shkschneider.awesome

import org.slf4j.LoggerFactory

object Logger {

    private val logger = LoggerFactory.getLogger(Awesome.ID)

    fun trace(msg: String) = logger.trace(msg)

    fun debug(msg: String) = logger.debug(msg)

    fun info(msg: String) = logger.debug(msg)

    fun warn(msg: String) = logger.debug(msg)

    fun error(msg: String) = logger.debug(msg)


}