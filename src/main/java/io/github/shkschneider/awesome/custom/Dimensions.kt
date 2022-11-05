package io.github.shkschneider.awesome.custom

import net.minecraft.world.World

object Dimensions {

    const val NETHER = -1
    const val OVERWORLD = 0
    const val END = 1
    const val UNKNOWN = Int.MIN_VALUE

    fun World.id(): Int {
        val identifier = dimensionKey.value
        return when (identifier.namespace) {
            "minecraft" -> when (identifier.path) {
                World.NETHER.value.namespace -> NETHER
                World.OVERWORLD.value.namespace -> OVERWORLD
                World.END.value.namespace -> END
                else -> throw IllegalStateException()
            }
            else -> UNKNOWN
        }
    }

    fun World.spawn() =
        Location(registryKey, levelProperties.spawnX.toDouble(), levelProperties.spawnY.toDouble(), levelProperties.spawnZ.toDouble())

    operator fun invoke() = Unit

}
