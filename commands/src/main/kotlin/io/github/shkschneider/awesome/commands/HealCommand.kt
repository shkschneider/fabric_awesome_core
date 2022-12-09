package io.github.shkschneider.awesome.commands

import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.core.AwesomeCommand
import io.github.shkschneider.awesome.custom.Permissions
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.network.ServerPlayerEntity

class HealCommand : AwesomeCommand.ForPlayer("heal", Permissions.Admin) {

    override fun run(context: CommandContext<ServerCommandSource>, player: ServerPlayerEntity): Int {
        player.heal(player.maxHealth)
        player.hungerManager.foodLevel = player.maxHealth.toInt()
        return SUCCESS
    }

}
