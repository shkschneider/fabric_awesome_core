package io.github.shkschneider.awesome.core

sealed class Permissions(val level: Int) {

    @Deprecated("You should specify at least Player -- Commands being recommended.")
    object Anyone : Permissions(0)
    object Player : Permissions(1)
    object Commands : Permissions(2)
    object Moderator : Permissions(3)
    object Admin : Permissions(4)

}
