package io.github.shkschneider.awesome.items

import io.github.shkschneider.awesome.core.AwesomeItem
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.minecraft.util.Identifier

sealed class AwesomeMaterials(
    id: Identifier,
    settings: Settings,
) : AwesomeItem(id, settings) {

    class Chip(
        name: String,
        settings: Settings,
    ) : AwesomeMaterials(AwesomeUtils.identifier("${name}_chip"), settings)

    class Dust(
        name: String,
        settings: Settings,
    ) : AwesomeMaterials(AwesomeUtils.identifier("${name}_dust"), settings)

    class Powder(
        name: String,
        settings: Settings,
    ) : AwesomeMaterials(AwesomeUtils.identifier("${name}_powder"), settings)

    class Ingot(
        name: String,
        settings: Settings,
    ) : AwesomeMaterials(AwesomeUtils.identifier("${name}_ingot"), settings)

    class Plate(
        name: String,
        settings: Settings,
    ) : AwesomeMaterials(AwesomeUtils.identifier("${name}_plate"), settings)

    class Rod(
        name: String,
        settings: Settings,
    ) : AwesomeMaterials(AwesomeUtils.identifier("${name}_rod"), settings)

    class Frame(
        name: String,
        settings: Settings,
    ) : AwesomeMaterials(AwesomeUtils.identifier("${name}_frame"), settings)

}
