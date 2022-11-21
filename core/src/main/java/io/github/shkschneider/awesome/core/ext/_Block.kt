package io.github.shkschneider.awesome.core.ext

import net.minecraft.block.Block
import net.minecraft.block.OreBlock
import net.minecraft.block.RedstoneOreBlock

val Block.isOre: Boolean get() =
    this is OreBlock || this is RedstoneOreBlock
