package io.github.shkschneider.awesome

sealed class AwesomePermissions(val level: Int) {

    object Player : AwesomePermissions(1)
    object Commands : AwesomePermissions(2)
    object Moderator : AwesomePermissions(3)
    object Admin : AwesomePermissions(4)

}