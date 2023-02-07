package io.github.shkschneider.awesome.crystals

import io.github.shkschneider.awesome.core.AwesomeUtils

object AwesomeCrystal {

    operator fun invoke(id: String) {
        val crystal = AwesomeCrystalBlock(AwesomeUtils.identifier("${id}_crystal"))
        AwesomeBuddingBlock(AwesomeUtils.identifier("budding_${id}"), crystal)
    }

}
