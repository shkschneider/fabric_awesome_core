package io.github.shkschneider.awesome.core

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.LiteralTextContent
import net.minecraft.text.MutableText
import net.minecraft.text.Text

object AwesomeChat {

    internal fun text(txt: String) = MutableText.of(LiteralTextContent(txt))

    fun message(player: PlayerEntity, text: Text) {
        player.sendMessage(text, false)
    }

    fun overlay(player: PlayerEntity, text: Text) {
        player.sendMessage(text, true)
    }

}
