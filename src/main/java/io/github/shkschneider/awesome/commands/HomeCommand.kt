package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.entities.IEntityData
import io.github.shkschneider.awesome.entities.readLocation
import io.github.shkschneider.awesome.entities.writeLocation
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

class HomeCommand : Command<ServerCommandSource> {

    override fun run(context: CommandContext<ServerCommandSource>?): Int {
        val player = context?.source?.player ?: return -1
        if (player.isInTeleportationState) return -2
        val data = (player as IEntityData).data
        val location = data.readLocation("home") ?: run {
            context.source.sendFeedback(Text.of("Homeless..."), false)
            return -3
        }
        // back
        player.writeLocation("back")
        // home
        context.source.sendFeedback(Text.of("Teleporting home $location..."), false)
        player.teleport(player.server.getWorld(location.worldRegistryKey), location.x, location.y, location.z, location.yaw, location.pitch)
        return 1
    }

}