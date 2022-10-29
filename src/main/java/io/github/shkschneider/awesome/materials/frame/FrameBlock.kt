package io.github.shkschneider.awesome.materials.frame

import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks

class FrameBlock : Block(
    FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
        .nonOpaque()
) {

    val ID = "frame"

    init {
        AwesomeMaterials.invoke(ID, this)
    }

}
