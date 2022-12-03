package io.github.shkschneider.awesome.core.ext

import io.github.shkschneider.awesome.core.AwesomeTime
import io.github.shkschneider.awesome.custom.AwesomeClock
import net.minecraft.world.World

fun World.clock(): AwesomeClock {
    val hour = (timeOfDay / AwesomeTime.ticksPerInGameHour).toInt()
    val minute = ((timeOfDay - (hour * AwesomeTime.ticksPerInGameHour)) / AwesomeTime.ticksPerInGameMinute).toInt()
    val ticks = (timeOfDay - (hour * AwesomeTime.ticksPerInGameHour) - (minute * AwesomeTime.ticksPerInGameMinute)).toInt()
    return AwesomeClock(6 + hour, minute, ticks)
}
