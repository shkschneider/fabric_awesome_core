package io.github.shkschneider.awesome.core

import com.mojang.brigadier.Command
import io.github.shkschneider.awesome.custom.Permissions
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralTextContent
import net.minecraft.text.MutableText
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

    private fun text(txt: String) = MutableText.of(LiteralTextContent(txt))

    fun sendMessage(source: ServerCommandSource, txt: String) {
        source.sendMessage(text(txt).formatted(Formatting.WHITE))
    }

    fun sendFeedback(source: ServerCommandSource, txt: String, broadcastToOps: Boolean = false) {
        source.sendFeedback(text(txt).formatted(Formatting.GRAY), broadcastToOps)
    }

    fun sendError(source: ServerCommandSource, txt: String? = null, code: Int): Int {
        check(code < 0)
        source.sendError(text(txt ?: "Error $code").formatted(Formatting.RED))
        return code
    }

}
