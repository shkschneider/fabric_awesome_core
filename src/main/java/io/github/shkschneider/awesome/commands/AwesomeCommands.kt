package io.github.shkschneider.awesome.commands

object AwesomeCommands {

    operator fun invoke() {
        MooCommand()
        FlyCommand()
        SetHomeCommand()
        HomeCommand()
        BackCommand()
    }

}