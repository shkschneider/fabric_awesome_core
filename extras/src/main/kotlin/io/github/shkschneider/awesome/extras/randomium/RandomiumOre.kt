package io.github.shkschneider.awesome.extras.randomium

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeColors
import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.ext.toVec3d
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.ExperienceDroppingBlock
import net.minecraft.block.ShapeContext
import net.minecraft.command.argument.EntityAnchorArgumentType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.DustParticleEffect
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import kotlin.math.max
import kotlin.math.min

class RandomiumOre(
    name: String,
) : ExperienceDroppingBlock(
    FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK)
) {

    init {
        init(AwesomeUtils.identifier(name))
    }

    private fun init(id: Identifier) {
        AwesomeRegistries.blockItem(id, this as Block, Awesome.GROUP)
    }

    @Suppress("DEPRECATION")
    override fun onBlockBreakStart(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity) {
        super.onBlockBreakStart(state, world, pos, player)
        particles(world, pos)
    }

    override fun onBreak(world: World, pos: BlockPos, state: BlockState, player: PlayerEntity) {
        super.onBreak(world, pos, state, player)
        if (world.registryKey != World.END) return
        val start = pos.mutableCopy().add(-1, -1, -1)
        val end = pos.mutableCopy().add(1, 1, 1)
        BlockPos.iterate(
            min(start.x, end.x), min(start.y, end.y), min(start.z, end.z),
            max(start.x, end.x), max(start.y, end.y), max(start.z, end.z),
        ).toList().filter {
            world.canPlace(state, it, ShapeContext.of(player))
        }.takeUnless { it.isEmpty() }?.random()?.let { tp ->
            player.teleport(tp.x.toDouble() + 0.5, tp.y.toDouble() + 0.25, tp.z.toDouble() + 0.5, false)
            player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, pos.toVec3d())
        }
    }

    // net.minecraft.block.RedstoneOreBlock.class
    private fun particles(world: World, pos: BlockPos) {
        val color = AwesomeColors.randomium
        val offset = 0.5625
        val directions = Direction.values()
        for (direction in directions) {
            val blockPos = pos.offset(direction)
            if (!world.getBlockState(blockPos).isOpaqueFullCube(world, blockPos)) {
                val e = if (direction.axis === Direction.Axis.X) 0.5 + offset * direction.offsetX.toDouble() else world.random.nextFloat().toDouble()
                val f = if (direction.axis === Direction.Axis.Y) 0.5 + offset * direction.offsetY.toDouble() else world.random.nextFloat().toDouble()
                val g = if (direction.axis === Direction.Axis.Z) 0.5 + offset * direction.offsetZ.toDouble() else world.random.nextFloat().toDouble()
                world.addParticle(
                    DustParticleEffect(Vec3d.unpackRgb(color).toVector3f(), 1.0F),
                    pos.x.toDouble() + e,
                    pos.y.toDouble() + f,
                    pos.z.toDouble() + g,
                    0.0,
                    0.0,
                    0.0
                )
            }
        }
    }

}
