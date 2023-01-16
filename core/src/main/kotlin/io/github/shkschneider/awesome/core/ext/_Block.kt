package io.github.shkschneider.awesome.core.ext

import net.minecraft.block.Block
import net.minecraft.block.OreBlock

val Block.isOre: Boolean get() =
    this is OreBlock // RedstoneOreBlock already drops xp
