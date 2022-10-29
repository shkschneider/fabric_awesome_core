package io.github.shkschneider.awesome.materials.tesseract

import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks

class TesseractBlock : Block(
    FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK)
        .luminance(1)
        .nonOpaque()
) {

    val ID = "tesseract"

    init {
        AwesomeMaterials.invoke(ID, this)
    }

}
