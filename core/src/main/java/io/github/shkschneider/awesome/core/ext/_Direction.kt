package io.github.shkschneider.awesome.core.ext

import io.github.shkschneider.awesome.custom.Faces
import net.minecraft.block.BlockState
import net.minecraft.state.property.DirectionProperty
import net.minecraft.util.math.Direction

internal fun Direction.toFace(): Faces =
    when (this) {
        Direction.UP -> Faces.Top
        Direction.DOWN -> Faces.Bottom
        else -> Faces.Side()
    }

fun Direction.relativeFace(state: BlockState): Faces {
    val property = state.properties.firstOrNull { it is DirectionProperty } ?: return toFace()
    val facing = (state.get(property) as Direction)
    return when {
        this == Direction.UP -> Faces.Top
        this == Direction.DOWN -> Faces.Bottom
        this == facing -> Faces.Front
        this == facing.opposite -> Faces.Back
        this.rotateYClockwise() == facing -> Faces.Side(left = false)
        else -> Faces.Side(right = false)
    }
}
