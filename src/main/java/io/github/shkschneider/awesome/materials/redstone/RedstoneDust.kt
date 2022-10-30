package io.github.shkschneider.awesome.materials.redstone

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.util.Rarity

class RedstoneDust : Item(
    FabricItemSettings()
        .maxCount(AwesomeUtils.STACK)
        .group(Awesome.GROUP)
        .rarity(Rarity.COMMON)
) {

    companion object {

        const val ID = "redstone_dust"

    }

    init {
        AwesomeMaterials.invoke(ID, this)
    }

}