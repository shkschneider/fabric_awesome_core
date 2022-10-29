package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.AwesomePermissions
import io.github.shkschneider.awesome.entities.IEntityData
import io.github.shkschneider.awesome.custom.writeLocation
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

class SetHomeCommand : AwesomeCommand("sethome", AwesomePermissions.Commands) {

    override fun run(context: CommandContext<ServerCommandSource>?): Int {
        val player = context?.source?.player ?: return -1
        val location = (player as IEntityData).writeLocation("home") ?: return -2
        context.source.sendFeedback(Text.of("Home set $location!"), false)
        return 1
    }

}
