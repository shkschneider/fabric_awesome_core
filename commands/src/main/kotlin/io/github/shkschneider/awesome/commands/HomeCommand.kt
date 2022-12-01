package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.core.AwesomeCommand
import io.github.shkschneider.awesome.custom.IEntityData
import io.github.shkschneider.awesome.custom.Location.Companion.readLocation
import io.github.shkschneider.awesome.custom.Location.Companion.writeLocation
import io.github.shkschneider.awesome.custom.Permissions
import net.minecraft.server.command.ServerCommandSource

class HomeCommand : AwesomeCommand("home", Permissions.Commands) {

    override fun run(context: CommandContext<ServerCommandSource>): Int {
        val player = context.source?.player ?: return sendError(context.source, code = -1)
        if (player.isInTeleportationState || player.isOnGround.not()) return sendError(context.source, code = -2)
        val data = (player as IEntityData).data
        val location = data.readLocation("home")?.safe() ?: run {
            return sendError(context.source, "Homeless...", -3)
        }
        // back
        player.writeLocation("back")
        // home
        sendFeedback(context.source, "Teleporting home @ $location...")
        player.teleport(player.server.getWorld(location.key), location.x, location.y, location.z, location.yaw, location.pitch)
        return SUCCESS
    }

}
