package io.github.shkschneider.awesome.core.ext

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import kotlin.math.roundToInt

fun Vec3d.center() =
    Vec3d(x.roundToInt() + 0.5, y.roundToInt() + 0.5, z.roundToInt() + 0.5)

fun Vec3d.toBlockPos() =
    BlockPos(this.x + 0.5, this.y + 0.5, this.z + 0.5)
