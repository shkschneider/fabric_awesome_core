package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.commands.BackCommand
import io.github.shkschneider.awesome.commands.BroadcastCommand
import io.github.shkschneider.awesome.commands.FlyCommand
import io.github.shkschneider.awesome.commands.HomeCommand
import io.github.shkschneider.awesome.commands.MooCommand
import io.github.shkschneider.awesome.commands.SetHomeCommand

object AwesomeCommands {

    operator fun invoke() {
        if (Awesome.CONFIG.commands) {
            BackCommand()
            BroadcastCommand()
            FlyCommand()
            HomeCommand()
            MooCommand()
            SetHomeCommand()
        }
    }

}
