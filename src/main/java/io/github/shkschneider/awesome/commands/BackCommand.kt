package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.custom.Permissions
import io.github.shkschneider.awesome.custom.Location.Companion.clearLocation
import io.github.shkschneider.awesome.custom.Location.Companion.readLocation
import io.github.shkschneider.awesome.entities.IEntityData
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

class BackCommand : AwesomeCommand("back", Permissions.Commands) {

    override fun run(context: CommandContext<ServerCommandSource>?): Int {
        val player = context?.source?.player ?: return -1
        if (player.isInTeleportationState) return -2
        val data = (player as IEntityData).data
        val location = data.readLocation("back") ?: run {
            context.source.sendFeedback(Text.of("Nowhere to go back to..."), false)
            return -3
        }
        data.clearLocation("back")
        context.source.sendFeedback(Text.of("Teleporting back $location..."), false)
        player.teleport(player.server.getWorld(location.key), location.x + 0.5, location.y + 0.25, location.z + 0.5, location.yaw, location.pitch)
        return 1
    }

}
