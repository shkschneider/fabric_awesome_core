package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.core.AwesomeCommand
import io.github.shkschneider.awesome.custom.Permissions
import net.minecraft.server.command.ServerCommandSource

class FlyCommand : AwesomeCommand("fly", Permissions.Moderator) {

    override fun run(context: CommandContext<ServerCommandSource>): Int {
        val player = context.source?.player ?: return sendError(context.source, code = -1)
        if (!player.abilities.allowFlying) {
            player.abilities.allowFlying = true
            // player.abilities.flySpeed = max(player.abilities.walkSpeed, player.abilities.flySpeed)
            sendFeedback(context.source, "${player.name.string} can now fly!", broadcastToOps = true)
        } else {
            player.abilities.allowFlying = false
            player.abilities.flying = false
            sendFeedback(context.source, "${player.name.string} cannot fly anymore!", broadcastToOps = true)
        }
        player.sendAbilitiesUpdate()
        return SUCCESS
    }

}
