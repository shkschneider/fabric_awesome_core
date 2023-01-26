package io.github.shkschneider.awesome.extras.budding

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeRegistries
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.AmethystBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random

abstract class AwesomeBuddingBlock(
    private val id: Identifier,
    private val block: Block,
) : AmethystBlock(FabricBlockSettings.copyOf(Blocks.BUDDING_AMETHYST)) {

    init {
        init()
    }

    private fun init() {
        AwesomeRegistries.blockItem(id, this as Block, Awesome.GROUP)
    }

    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
        if (random.nextInt(5) == 0) {
            val blockPos = pos.offset(Direction.values().random())
            if (world.getBlockState(blockPos).isAir) {
                world.setBlockState(blockPos, block.defaultState)
            }
        }
    }

    override fun getPistonBehavior(state: BlockState): PistonBehavior = PistonBehavior.DESTROY

}
