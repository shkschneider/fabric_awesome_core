package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.custom.Permissions
import net.minecraft.server.command.ServerCommandSource

class FlyCommand : AwesomeCommand("fly", Permissions.Moderator) {

    override fun run(context: CommandContext<ServerCommandSource>?): Int {
        val player = context?.source?.player ?: return -1
        if (player.abilities.allowFlying) {
            player.abilities.allowFlying = false
            player.abilities.flying = false
        } else {
            player.abilities.allowFlying = true
        }
        player.sendAbilitiesUpdate()
        return 1
    }

}
