package io.github.shkschneider.awesome.core

import io.github.shkschneider.awesome.Awesome
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object AwesomeLogger {

    var TAG = Awesome.NAME.uppercase()

    private val logger: Logger = LogManager.getLogger(Awesome.ID)

    private fun stdout(lvl: String, msg: String) {
        println("$TAG/${lvl.uppercase()}: $msg")
    }

    fun trace(throwable: Throwable) = logger.trace(throwable).also { throwable.printStackTrace() }

    fun debug(msg: String) = logger.debug(msg).also { stdout("debug", msg) }

    fun info(msg: String) = logger.info(msg).also { stdout("info", msg) }

    fun warn(msg: String) = logger.warn(msg).also { stdout("warn", msg) }

    fun error(msg: String) = logger.error(msg).also { stdout("error", msg) }


}
