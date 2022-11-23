package io.github.shkschneider.awesome.custom

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

}
