package io.github.shkschneider.awesome.core.ext

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3d

fun BlockPos.toVec3d() =
    Vec3d(x.toDouble(), y.toDouble(), z.toDouble())

fun BlockPos.toBox(radius: Double = 1.0) =
    Box.of(toVec3d().center(), radius, radius, radius)
