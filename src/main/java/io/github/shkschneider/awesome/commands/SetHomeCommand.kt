package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.core.AwesomeCommand
import io.github.shkschneider.awesome.custom.Location.Companion.writeLocation
import io.github.shkschneider.awesome.custom.Permissions
import io.github.shkschneider.awesome.custom.IEntityData
import net.minecraft.server.command.ServerCommandSource

class SetHomeCommand : AwesomeCommand("sethome", Permissions.Commands) {

    override fun run(context: CommandContext<ServerCommandSource>): Int {
        val player = context.source?.player ?: return sendError(context.source, code = -1)
        if (player.isInTeleportationState || player.isOnGround.not()) return sendError(context.source, code = -2)
        val location = (player as IEntityData).writeLocation("home")?.safe() ?: return sendError(context.source, code = -3)
        sendFeedback(context.source, "Home set @ $location!")
        return SUCCESS
    }

}
