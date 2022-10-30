package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.ServerCommandSource

class FlyCommand : Command<ServerCommandSource> {

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