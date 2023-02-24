package io.github.shkschneider.awesome.core.ext

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.tag.BlockTags

val BlockState.isOre: Boolean get() =
    this.isIn(BlockTags.IRON_ORES)
            || this.isIn(BlockTags.COPPER_ORES)
            || this.isIn(BlockTags.REDSTONE_ORES)
            || this.isIn(BlockTags.GOLD_ORES)
            || this.isIn(BlockTags.COAL_ORES)
            || this.isIn(BlockTags.DIAMOND_ORES)
            || this.isIn(BlockTags.LAPIS_ORES)
            || this.isIn(BlockTags.EMERALD_ORES)
            || this.block == Blocks.NETHER_QUARTZ_ORE
            || this.block == Blocks.ANCIENT_DEBRIS
