package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.core.AwesomeCommand
import io.github.shkschneider.awesome.custom.Permissions
import net.minecraft.server.command.ServerCommandSource

class HealCommand : AwesomeCommand("heal", Permissions.Admin) {

    override fun run(context: CommandContext<ServerCommandSource>): Int {
        val player = context.source?.player ?: return sendError(context.source, code = -1)
        player.heal(player.maxHealth)
        player.hungerManager.foodLevel = player.maxHealth.toInt()
        return SUCCESS
    }

}
