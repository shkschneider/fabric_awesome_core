package io.github.shkschneider.awesome.custom

import net.minecraft.block.BlockState
import net.minecraft.state.property.DirectionProperty
import net.minecraft.util.math.Direction

sealed class Faces {

    object Top : Faces()
    object Bottom : Faces()
    object Front : Faces()
    object Back : Faces()
    data class Side(val right: Boolean = true, val left: Boolean = true) : Faces() {

        override fun toString(): String =
            (this as Faces).toString() + if (right) "(right)" else "(left)"

    }

    override fun toString(): String {
        return this::class.java.simpleName
    }

    companion object {

        private fun Direction.toFace(): Faces =
            when (this) {
                Direction.UP -> Top
                Direction.DOWN -> Bottom
                else -> Side()
            }

        fun Direction.relativeFace(state: BlockState): Faces {
            val property = state.properties.firstOrNull { it is DirectionProperty } ?: return toFace()
            val facing = (state.get(property) as Direction)
            return when {
                this == Direction.UP -> Top
                this == Direction.DOWN -> Bottom
                this == facing -> Front
                this == facing.opposite -> Back
                this.rotateYClockwise() == facing -> Side(left = false)
                else -> Side(right = false)
            }
        }

    }

}
