package io.github.shkschneider.awesome.custom

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.entities.IEntityData
import net.minecraft.entity.Entity
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.World

data class Location(
    val dim: String, // RegistryKey<World>
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float = 0F,
    val pitch: Float = 0F,
) {

    val worldRegistryKey: RegistryKey<World> get() =
        RegistryKey.of(Registry.WORLD_KEY, Identifier.tryParse(dim))

    override fun toString(): String {
        return "in $dim at ${x.toInt()},${y.toInt()},${z.toInt()}"
    }

}

fun <T : IEntityData> T.writeLocation(prefix: String): Location? {
    val entity = (this as? Entity) ?: return null
    return Location(entity.world.registryKey.value.toString(), entity.x, entity.y, entity.z, entity.yaw, entity.pitch).also {
        data.putString(AwesomeUtils.key(prefix, "dim"), entity.world.registryKey.value.toString())
        data.putDouble(AwesomeUtils.key(prefix, "x"), entity.x)
        data.putDouble(AwesomeUtils.key(prefix, "y"), entity.y)
        data.putDouble(AwesomeUtils.key(prefix, "z"), entity.z)
        data.putFloat(AwesomeUtils.key(prefix, "yaw"), entity.yaw)
        data.putFloat(AwesomeUtils.key(prefix, "pitch"), entity.pitch)
    }
}

fun NbtCompound.readLocation(prefix: String): Location? {
    if (!contains(AwesomeUtils.key(prefix, "dim")) || !contains(AwesomeUtils.key(prefix, "x")) || !contains(AwesomeUtils.key(prefix, "y")) || !contains(AwesomeUtils.key(prefix, "z"))) return null
    return Location(
        getString(AwesomeUtils.key(prefix, "dim")),
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