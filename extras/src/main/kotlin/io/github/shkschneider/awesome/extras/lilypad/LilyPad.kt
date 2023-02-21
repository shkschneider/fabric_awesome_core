package io.github.shkschneider.awesome.extras.lilypad

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeColors
import io.github.shkschneider.awesome.core.AwesomeParticles
import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.ext.id
import io.github.shkschneider.awesome.core.ext.positions
import io.github.shkschneider.awesome.custom.Minecraft
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.Fertilizable
import net.minecraft.block.LilyPadBlock
import net.minecraft.block.ShapeContext
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.item.PlaceableOnWaterItem
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World

class LilyPad : LilyPadBlock(
    FabricBlockSettings.copyOf(Blocks.LILY_PAD).collidable(false).nonOpaque().ticksRandomly(),
) {

    init {
        val block = AwesomeRegistries.block(AwesomeUtils.identifier("lily_pad"), this)
        val blockItem = PlaceableOnWaterItem(block, FabricItemSettings())
        AwesomeRegistries.blockItem(id(), blockItem, Awesome.GROUP)
        if (Minecraft.isClient) AwesomeRegistries.blockRenderer(block, RenderLayer.getCutout())
    }

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape =
        createCuboidShape(2.0, 0.0, 2.0, 14.0, 14.0, 14.0)

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean = false

    override fun randomTick(state: BlockState, world: ServerWorld, blockPos: BlockPos, random: Random) {
        if (world.isClient) return
        Box(blockPos).expand(1.0).positions().forEach { pos ->
            while (world.getBlockState(pos).block is Fertilizable) {
                val block = world.getBlockState(pos).block as? Fertilizable ?: break
                if (block.isFertilizable(world, pos, world.getBlockState(pos), world.isClient)) {
                    block.grow(world, random, pos, world.getBlockState(pos))
                } else {
                    break
                }
            }
        }
    }

    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: Random) {
        AwesomeParticles(world, pos, color = AwesomeColors.lilyPadItem, offset = 0.5625 / 2.0)
    }

}
