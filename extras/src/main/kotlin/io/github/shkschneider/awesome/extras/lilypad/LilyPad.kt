package io.github.shkschneider.awesome.extras.lilypad

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeColors
import io.github.shkschneider.awesome.core.AwesomeParticles
import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.ext.positions
import io.github.shkschneider.awesome.custom.Minecraft
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.Fertilizable
import net.minecraft.block.GrassBlock
import net.minecraft.block.KelpPlantBlock
import net.minecraft.block.ShapeContext
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.Entity
import net.minecraft.entity.vehicle.BoatEntity
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldView
import java.util.Random

class LilyPad : KelpPlantBlock(
    FabricBlockSettings.copyOf(Blocks.LILY_PAD).collidable(false).nonOpaque().ticksRandomly(),
) {

    init {
        val block = AwesomeRegistries.blockWithItem(AwesomeUtils.identifier("lily_pad"), this, Awesome.GROUP).block
        if (Minecraft.isClient) AwesomeRegistries.blockRenderer(block, RenderLayer.getCutout())
    }

    override fun canAttachTo(state: BlockState): Boolean = false

    override fun canGrow(world: World, random: Random, pos: BlockPos, state: BlockState): Boolean = false

    override fun getPickStack(world: BlockView, pos: BlockPos, state: BlockState): ItemStack =
        ItemStack(this, 1)

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape =
        createCuboidShape(2.0, 0.0, 2.0, 14.0, 14.0, 14.0)

    override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        if (world is ServerWorld && entity is BoatEntity) {
            world.breakBlock(BlockPos(pos), true, entity)
        }
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean =
        world.getFluidState(pos).fluid == Fluids.WATER && world.getFluidState(pos.up()).fluid == Fluids.EMPTY

    override fun randomTick(state: BlockState, world: ServerWorld, blockPos: BlockPos, random: Random) {
        if (world.isClient) return
        Box(blockPos).expand(1.0).positions().forEach { pos ->
            while (world.getBlockState(pos).block is Fertilizable) {
                val block = world.getBlockState(pos).block as? Fertilizable ?: break
                if (block !is GrassBlock && block.isFertilizable(world, pos, world.getBlockState(pos), world.isClient)) {
                    block.grow(world, random, pos, world.getBlockState(pos))
                } else {
                    continue
                }
            }
        }
    }

    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: Random) {
        AwesomeParticles(world, pos.up(), color = AwesomeColors.lilyPad, offset = 0.5625 / 2.0)
    }

}
