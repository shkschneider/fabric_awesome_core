package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import io.github.shkschneider.awesome.custom.Permissions
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

abstract class AwesomeCommand(
    open val command: String,
    open val permission: Permissions,
) : Command<ServerCommandSource> {

    init {
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher: CommandDispatcher<ServerCommandSource?>, _, _ ->
            dispatcher.register(CommandManager.literal(command).requires { it.hasPermissionLevel(permission.level) }.executes(::run))
        })
    }

}
