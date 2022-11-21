package io.github.shkschneider.awesome.core

import com.mojang.brigadier.Command
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.util.Formatting

abstract class AwesomeCommand(
    val name: String,
    permission: Permissions,
) : Command<ServerCommandSource> {

    companion object {

        const val SUCCESS = 1

    }

    init {
        AwesomeRegistries.command(name, permission, ::run)
    }

    fun sendMessage(source: ServerCommandSource, txt: String) {
        source.sendMessage(AwesomeChat.text(txt).formatted(Formatting.WHITE))
    }

    fun sendFeedback(source: ServerCommandSource, txt: String, broadcastToOps: Boolean = false) {
        source.sendFeedback(AwesomeChat.text(txt).formatted(Formatting.GRAY), broadcastToOps)
    }

    fun sendError(source: ServerCommandSource, txt: String? = null, code: Int): Int {
        check(code < 0)
        source.sendError(AwesomeChat.text(txt ?: "Error $code").formatted(Formatting.RED))
        return code
    }

    fun broadcast(source: ServerCommandSource, txt: String) {
        source.world.players.forEach { player ->
            AwesomeChat.message(player, AwesomeChat.text(txt).formatted(Formatting.GRAY))
        }
    }

}
