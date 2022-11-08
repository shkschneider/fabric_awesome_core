package io.github.shkschneider.awesome.core

import io.github.shkschneider.awesome.Awesome
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object AwesomeLogger {

    private val TAG = Awesome.NAME

    private val logger: Logger = LoggerFactory.getLogger(TAG)

    fun trace(throwable: Throwable) = logger.trace(throwable.toString()).also { throwable.printStackTrace() }

    fun debug(msg: String) {
        // Minecraft has isDevelopment always false in my environment
        if (Minecraft.isDevelopment) println("[$TAG] $msg")
    }

    fun info(msg: String) = logger.info(msg)

    fun warn(msg: String) = logger.warn(msg)

    fun error(msg: String) = logger.error(msg)

}
