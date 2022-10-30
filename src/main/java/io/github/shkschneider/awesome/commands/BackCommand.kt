package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.AwesomePermissions
import io.github.shkschneider.awesome.entities.IEntityData
import io.github.shkschneider.awesome.custom.clearLocation
import io.github.shkschneider.awesome.custom.readLocation
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

class BackCommand : AwesomeCommand("back", AwesomePermissions.Commands) {

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
        player.teleport(player.server.getWorld(location.worldRegistryKey), location.x + 0.5, location.y + 0.25, location.z + 0.5, location.yaw, location.pitch)
        return 1
    }

}