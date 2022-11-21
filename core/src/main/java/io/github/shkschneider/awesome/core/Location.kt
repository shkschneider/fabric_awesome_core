package io.github.shkschneider.awesome.core

import net.minecraft.entity.Entity
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.World
import kotlin.math.roundToInt

data class Location(
    val key: RegistryKey<World>,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float = 0F,
    val pitch: Float = 0F,
) {

    override fun toString(): String {
        return "${key.value.path}:${x.roundToInt()},${y.roundToInt()},${z.roundToInt()}"
    }

    fun safe() = copy(x = x.roundToInt().toDouble(), y = y.roundToInt().toDouble(), z = z.roundToInt().toDouble())

    fun offset() = safe().copy(x = x + 0.5, y = y + 0.25, z = z + 0.5)

    companion object {

        fun <T : IEntityData> T.writeLocation(prefix: String): Location? {
            val entity = (this as? Entity) ?: return null
            return Location(entity.world.registryKey, entity.x, entity.y, entity.z, entity.yaw, entity.pitch).also {
                data.putString(AwesomeUtils.key(prefix, "dim"), entity.world.registryKey.value.toString())
                data.putDouble(AwesomeUtils.key(prefix, "x"), entity.x)
                data.putDouble(AwesomeUtils.key(prefix, "y"), entity.y)
                data.putDouble(AwesomeUtils.key(prefix, "z"), entity.z)
                data.putFloat(AwesomeUtils.key(prefix, "yaw"), entity.yaw)
                data.putFloat(AwesomeUtils.key(prefix, "pitch"), entity.pitch)
            }
        }

        fun NbtCompound.readLocation(prefix: String): Location? {
            if (
                !contains(AwesomeUtils.key(prefix, "dim"))
                || !contains(AwesomeUtils.key(prefix, "x"))
                || !contains(AwesomeUtils.key(prefix, "y"))
                || !contains(AwesomeUtils.key(prefix, "z"))
            ) return null
            return Location(
                RegistryKey.of(Registry.WORLD_KEY, Identifier.tryParse(getString(AwesomeUtils.key(prefix, "dim")))),
                getDouble(AwesomeUtils.key(prefix, "x")),
                getDouble(AwesomeUtils.key(prefix, "y")),
                getDouble(AwesomeUtils.key(prefix, "z")),
                getFloat(AwesomeUtils.key(prefix, "yaw")),
                getFloat(AwesomeUtils.key(prefix, "pitch")),
            )
        }

        fun NbtCompound.clearLocation(prefix: String) {
            remove(AwesomeUtils.key(prefix, "dim"))
            remove(AwesomeUtils.key(prefix, "x"))
            remove(AwesomeUtils.key(prefix, "y"))
            remove(AwesomeUtils.key(prefix, "z"))
            remove(AwesomeUtils.key(prefix, "yaw"))
            remove(AwesomeUtils.key(prefix, "pitch"))
        }

    }

}
