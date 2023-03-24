package io.github.shkschneider.awesome.core.ext

import io.github.shkschneider.awesome.core.AwesomeClock
import io.github.shkschneider.awesome.core.AwesomeTime
import io.github.shkschneider.awesome.custom.Location
import io.github.shkschneider.awesome.custom.Travels
import net.minecraft.world.World

fun World.id(): Int {
    val identifier = registryKey.value
    return when (identifier.namespace) {
        "minecraft" -> when (identifier.path) {
            World.NETHER.value.namespace -> Travels.NETHER
            World.OVERWORLD.value.namespace -> Travels.OVERWORLD
            World.END.value.namespace -> Travels.END
            else -> throw IllegalStateException()
        }
        else -> Travels.UNKNOWN
    }
}

fun World.spawn() =
    Location(registryKey, levelProperties.spawnX.toDouble(), levelProperties.spawnY.toDouble(), levelProperties.spawnZ.toDouble())

fun World.clock(): AwesomeClock {
    val clock = AwesomeClock.elapsed(timeOfDay + 6 * AwesomeTime.ticksPerInGameHour)
    return AwesomeClock(clock.days, clock.hours, clock.minutes, clock.ticks)
}
