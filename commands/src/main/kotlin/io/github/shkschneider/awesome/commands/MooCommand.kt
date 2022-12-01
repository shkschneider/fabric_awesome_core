package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.core.AwesomeCommand
import io.github.shkschneider.awesome.custom.Permissions
import net.minecraft.server.command.ServerCommandSource

class MooCommand : AwesomeCommand("moo", Permissions.Player) {

    override fun run(context: CommandContext<ServerCommandSource>): Int {
        sendFeedback(context.source, "Have you mooed today?")
        return SUCCESS
    }

}
