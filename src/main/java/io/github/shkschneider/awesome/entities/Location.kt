package io.github.shkschneider.awesome.entities

import io.github.shkschneider.awesome.Awesome
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
        data.putString(Awesome.id("${prefix}_dim"), entity.world.registryKey.value.toString())
        data.putDouble(Awesome.id("${prefix}_x"), entity.x)
        data.putDouble(Awesome.id("${prefix}_y"), entity.y)
        data.putDouble(Awesome.id("${prefix}_z"), entity.z)
        data.putFloat(Awesome.id("${prefix}_yaw"), entity.yaw)
        data.putFloat(Awesome.id("${prefix}_pitch"), entity.pitch)
    }
}

fun NbtCompound.readLocation(prefix: String): Location? {
    if (!contains(Awesome.id("${prefix}_dim")) || !contains(Awesome.id("${prefix}_x")) || !contains(Awesome.id("${prefix}_y")) || !contains(Awesome.id("${prefix}_z"))) return null
    return Location(
        getString(Awesome.id("${prefix}_dim")),
        getDouble(Awesome.id("${prefix}_x")),
        getDouble(Awesome.id("${prefix}_y")),
        getDouble(Awesome.id("${prefix}_z")),
        getFloat(Awesome.id("${prefix}_yaw")),
        getFloat(Awesome.id("${prefix}_pitch")),
    )
}

fun NbtCompound.clearLocation(prefix: String) {
    remove(Awesome.id("${prefix}_dim"))
    remove(Awesome.id("${prefix}_x"))
    remove(Awesome.id("${prefix}_y"))
    remove(Awesome.id("${prefix}_z"))
    remove(Awesome.id("${prefix}_yaw"))
    remove(Awesome.id("${prefix}_pitch"))
}