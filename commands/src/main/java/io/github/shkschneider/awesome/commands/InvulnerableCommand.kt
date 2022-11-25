package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.core.AwesomeCommand
import io.github.shkschneider.awesome.custom.Permissions
import net.minecraft.server.command.ServerCommandSource

class InvulnerableCommand : AwesomeCommand("invulnerable", Permissions.Moderator) {

    override fun run(context: CommandContext<ServerCommandSource>): Int {
        val player = context.source?.player ?: return sendError(context.source, code = -1)
        if (!player.abilities.invulnerable) {
            player.abilities.invulnerable = true
            sendFeedback(context.source, "${player.name.string} is now invulnerable!", broadcastToOps = true)
        } else {
            player.abilities.invulnerable = false
            sendFeedback(context.source, "${player.name.string} is vulnerable again!", broadcastToOps = true)
        }
        player.sendAbilitiesUpdate()
        return SUCCESS
    }

}
