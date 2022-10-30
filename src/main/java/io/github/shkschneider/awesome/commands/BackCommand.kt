package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.entities.IEntityData
import io.github.shkschneider.awesome.entities.clearLocation
import io.github.shkschneider.awesome.entities.readLocation
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

class BackCommand : Command<ServerCommandSource> {

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
        player.teleport(player.server.getWorld(location.worldRegistryKey), location.x, location.y, location.z, location.yaw, location.pitch)
        return 1
    }

}