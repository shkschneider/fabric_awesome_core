package io.github.shkschneider.awesome.core.ext

import io.github.shkschneider.awesome.core.AwesomeTime
import io.github.shkschneider.awesome.custom.AwesomeClock
import net.minecraft.world.World

fun World.clock(): AwesomeClock {
    val clock = AwesomeClock.elapsed(timeOfDay + 6 * AwesomeTime.ticksPerInGameHour)
    return AwesomeClock(clock.days, clock.hours, clock.minutes, clock.ticks)
}
