package io.github.shkschneider.awesome.crystals

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeRegistries
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.BuddingAmethystBlock
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random

class AwesomeBuddingBlock(
    protected val id: Identifier,
    protected val block: Block,
) : BuddingAmethystBlock(FabricBlockSettings.copyOf(Blocks.BUDDING_AMETHYST)) {

    init {
        init()
    }

    private fun init() {
        AwesomeRegistries.blockItem(id, this as Block, Awesome.GROUP)
    }

    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
        if (random.nextInt(5) == 0) {
            val side = Direction.values().random()
            val blockPos = pos.offset(side)
            if (world.getBlockState(blockPos).isAir) {
                world.setBlockState(blockPos, block.defaultState.with(Properties.FACING, side))
            }
        }
    }

    override fun getPistonBehavior(state: BlockState): PistonBehavior = PistonBehavior.DESTROY

}
