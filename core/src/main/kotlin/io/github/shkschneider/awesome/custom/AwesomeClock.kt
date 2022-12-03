package io.github.shkschneider.awesome.custom

import io.github.shkschneider.awesome.core.AwesomeTime

data class AwesomeClock(
    val hour: Int,
    val minute: Int,
    val ticks: Int,
) {

    val isZenith: Boolean get() =
        hour == 12 && minute == 0 && ticks == 0

    val isNadir: Boolean get() =
        hour == 0 && minute == 0 && ticks == 0

    fun toTicks(): Long =
        (hour * AwesomeTime.ticksPerInGameHour + minute * AwesomeTime.ticksPerInGameMinute + ticks).toLong()

    override fun toString(): String =
        "${hour}:${minute}.${ticks}"

}
