package io.github.shkschneider.awesome.items

import io.github.shkschneider.awesome.core.AwesomeItem
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.minecraft.util.Identifier

sealed class AwesomeMaterials(
    id: Identifier,
    settings: Settings,
) : AwesomeItem(id, settings) {

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
