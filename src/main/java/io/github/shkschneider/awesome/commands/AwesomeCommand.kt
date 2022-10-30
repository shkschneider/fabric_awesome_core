package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import io.github.shkschneider.awesome.AwesomePermissions
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource

class AwesomeCommand<T : Command<ServerCommandSource>>(
    permission: AwesomePermissions,
    command: String,
    callback: T,
) {

    init {
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher: CommandDispatcher<ServerCommandSource?>, _, _ ->
            dispatcher.register(CommandManager.literal(command).requires { it.hasPermissionLevel(permission.level) }.executes(callback::run))
        })
    }

}