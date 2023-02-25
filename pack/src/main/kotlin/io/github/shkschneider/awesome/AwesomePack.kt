package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.pack.GameRulesOverrides
import io.github.shkschneider.awesome.pack.PlayerHeads

object AwesomePack {

    operator fun invoke() {
        GameRulesOverrides()
        PlayerHeads()
    }

}

