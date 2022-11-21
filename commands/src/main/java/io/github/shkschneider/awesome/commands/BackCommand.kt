package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.core.AwesomeCommand
import io.github.shkschneider.awesome.core.IEntityData
import io.github.shkschneider.awesome.core.Location.Companion.clearLocation
import io.github.shkschneider.awesome.core.Location.Companion.readLocation
import io.github.shkschneider.awesome.core.Permissions
import net.minecraft.server.command.ServerCommandSource

class BackCommand : AwesomeCommand("back", Permissions.Commands) {

    override fun run(context: CommandContext<ServerCommandSource>): Int {
        val player = context.source?.player ?: return sendError(context.source, code = -1)
        if (player.isInTeleportationState || player.isOnGround.not()) return sendError(context.source, code = -2)
        val data = (player as IEntityData).data
        val location = data.readLocation("back")?.safe() ?: run {
            return sendError(context.source, "Nowhere to go back to...", -3)
        }
        data.clearLocation("back")
        sendFeedback(context.source, "Teleporting back @ $location...")
        player.teleport(player.server.getWorld(location.key), location.x, location.y, location.z, location.yaw, location.pitch)
        return SUCCESS
    }

}
