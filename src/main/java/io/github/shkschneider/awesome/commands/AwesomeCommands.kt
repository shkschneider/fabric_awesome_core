package io.github.shkschneider.awesome.commands

import io.github.shkschneider.awesome.Awesome

object AwesomeCommands {

    operator fun invoke() {
        if (Awesome.CONFIG.commands) {
            MooCommand()
            FlyCommand()
            SetHomeCommand()
            HomeCommand()
            BackCommand()
        }
    }

}
