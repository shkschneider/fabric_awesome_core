package io.github.shkschneider.awesome.commands

import io.github.shkschneider.awesome.AwesomePermissions

object AwesomeCommands {

    operator fun invoke() {
        AwesomeCommand(AwesomePermissions.Player, "moo", MooCommand())
        AwesomeCommand(AwesomePermissions.Admin, "fly", FlyCommand())
        AwesomeCommand(AwesomePermissions.Commands, "sethome", SetHomeCommand())
        AwesomeCommand(AwesomePermissions.Commands, "home", HomeCommand())
        AwesomeCommand(AwesomePermissions.Commands, "back", BackCommand())
    }

}