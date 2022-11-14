package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.arguments.StringArgumentType.string
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument
import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.core.AwesomeChat
import io.github.shkschneider.awesome.core.AwesomeCommand
import io.github.shkschneider.awesome.custom.Permissions
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.util.Formatting

class BroadcastCommand {

    private val name = "broadcast"
    private val permission = Permissions.Moderator
    private val ARG0 = "txt"

    init {
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher: CommandDispatcher<ServerCommandSource?>, _, _ ->
            dispatcher.register(
                CommandManager.literal(name)
                    .requires { it.hasPermissionLevel(permission.level) }
                    .then(argument<ServerCommandSource, String>(ARG0, string()).executes(::run)))
        })
    }

    private fun run(ctx: CommandContext<ServerCommandSource>): Int {
        ctx.source.world.players.forEach { player ->
            AwesomeChat.overlay(player, AwesomeChat.text(getString(ctx, ARG0))
                .formatted(Formatting.BOLD)
                .formatted(Formatting.RED)
            )
        }
        return AwesomeCommand.SUCCESS
    }

}
