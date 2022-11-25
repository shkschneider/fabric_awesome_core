package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.commands.BackCommand
import io.github.shkschneider.awesome.commands.BroadcastCommand
import io.github.shkschneider.awesome.commands.FlyCommand
import io.github.shkschneider.awesome.commands.HomeCommand
import io.github.shkschneider.awesome.commands.InvulnerableCommand
import io.github.shkschneider.awesome.commands.MooCommand
import io.github.shkschneider.awesome.commands.SetHomeCommand
import io.github.shkschneider.awesome.commands.SpawnCommand
import io.github.shkschneider.awesome.commands.TopCommand

object AwesomeCommands {

    operator fun invoke() {
        BackCommand()
        BroadcastCommand()
        FlyCommand()
        HomeCommand()
        InvulnerableCommand()
        MooCommand()
        SetHomeCommand()
        SpawnCommand()
        TopCommand()
    }

}
