package io.github.shkschneider.awesome.blocks

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.core.AwesomeBlock
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Blocks

class FrameBlock : AwesomeBlock(
    id = AwesomeUtils.identifier("frame"),
    settings = FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque(),
)
