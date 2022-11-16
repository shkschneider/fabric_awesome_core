package io.github.shkschneider.awesome.core

import io.github.shkschneider.awesome.Awesome
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity

class FluidStack(
    id: Identifier,
) : AwesomeItem(id, FabricItemSettings().group(Awesome.GROUP).rarity(Rarity.COMMON).fireproof().maxCount(1024))
