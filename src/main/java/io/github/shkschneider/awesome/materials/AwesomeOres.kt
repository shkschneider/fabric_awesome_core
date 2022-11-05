package io.github.shkschneider.awesome.materials

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.core.AwesomeItem
import net.minecraft.util.Identifier

sealed class AwesomeOres(
    id: Identifier,
    settings: Settings,
) : AwesomeItem(id, settings) {

    class Chip(
        name: String,
        settings: Settings,
    ) : AwesomeOres(AwesomeUtils.identifier("${name}_chip"), settings)

    class Dust(
        name: String,
        settings: Settings,
    ) : AwesomeOres(AwesomeUtils.identifier("${name}_dust"), settings)

    class Powder(
        name: String,
        settings: Settings,
    ) : AwesomeOres(AwesomeUtils.identifier("${name}_powder"), settings)

}
