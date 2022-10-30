package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

class MooCommand : Command<ServerCommandSource> {

    override fun run(context: CommandContext<ServerCommandSource>?): Int {
        context?.source?.sendFeedback(Text.of("Have you mooed today?"), false)
        return 1
    }

}