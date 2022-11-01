package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.custom.Location
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import kotlin.math.pow
import kotlin.math.sqrt

object AwesomeUtils {

    const val TICK = 20

    const val STACK = 64

    fun id(id: String) = "${Awesome.ID}:$id"

    fun identifier(id: String) = Identifier(id(id))

    fun key(vararg k: String) = "${Awesome.ID}_${k.joinToString(separator = "_") { it.lowercase() }}"

    fun translatable(vararg k: String) = "${k.first().lowercase()}.${Awesome.ID}.${k.drop(1).joinToString(separator = ".") { it.lowercase() }}"

    fun secondsToTicks(s: Int): Int = s * TICK

    fun ticksToSeconds(t: Int): Int = t / TICK

    // https://minecraft.fandom.com/wiki/Tutorials/Measuring_distance#Euclidean_distance_in_3_dimensions_(including_elevation)
    fun distance(l1: Location, l2: Location): Double {
        if (l1.dim != l2.dim) return Double.MAX_VALUE
        return sqrt(
            (l1.x - l2.x).pow(2) + (l1.y - l2.y).pow(2) + (l1.z - l2.z).pow(2)
        )
    }

    // NOTE: this might contain more than STACK elements
    fun List<ItemStack>.uniq(): List<ItemStack> = buildList {
        forEach { stack ->
            add(ItemStack(stack.item, filter { it.item == stack.item }.sumOf { it.count }))
        }
    }

}