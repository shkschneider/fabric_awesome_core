package io.github.shkschneider.awesome.obelisk

import io.github.shkschneider.awesome.core.AwesomeBlockWithEntity
import io.github.shkschneider.awesome.core.AwesomeColors
import io.github.shkschneider.awesome.core.AwesomeSounds
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.ext.toVec3d
import io.github.shkschneider.awesome.custom.Experience
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.custom.Minecraft
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.ExperienceOrbEntity
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.loot.context.LootContext
import net.minecraft.loot.context.LootContextParameters
import net.minecraft.particle.DustParticleEffect
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.world.BlockView
import net.minecraft.world.World

class ObeliskBlock : AwesomeBlockWithEntity<ObeliskBlockEntity>(
    AwesomeUtils.identifier("obelisk"), FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque().noBlockBreakParticles(),
) {

    override fun appendTooltip(stack: ItemStack, world: BlockView?, tooltip: MutableList<Text>, options: TooltipContext) {
        tooltip.add(Text.translatable(AwesomeUtils.translatable("block", id.path, "hint")).formatted(Formatting.GRAY))
    }

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean = false

    override fun createBlockEntity(pos: BlockPos, state: BlockState): ObeliskBlockEntity =
        ObeliskBlockEntity(id.path, entityType, pos, state, InputOutput(inputs = 1), 0 to 0)

    @Suppress("DEPRECATION")
    override fun getDroppedStacks(state: BlockState, builder: LootContext.Builder): MutableList<ItemStack> = mutableListOf(
        ItemStack(this, 1).also {
            // do not spawn bottled experience
            (builder.get(LootContextParameters.BLOCK_ENTITY) as? ObeliskBlockEntity)?.let { blockEntity ->
                ExperienceOrbEntity.spawn(builder.world, blockEntity.pos.toVec3d(), Experience.experienceFromLevel(blockEntity.bottles))
            }
        }
    )

    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hit: BlockHitResult): ActionResult {
        if (world.isClient) return ActionResult.PASS
        if (player.isAlive && hand == Hand.MAIN_HAND && player.mainHandStack.isEmpty) {
            return (world.getBlockEntity(pos) as? ObeliskBlockEntity)?.let { blockEntity ->
                if (!player.isSneaking) {
                    blockEntity.store(player)
                } else {
                    blockEntity.retrieve(player).also { result ->
                        if (result == ActionResult.SUCCESS) {
                            AwesomeSounds(world to pos, AwesomeSounds.experience)
                        }
                    }
                }
            } ?: ActionResult.PASS
        }
        return ActionResult.FAIL
    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: ObeliskBlockEntity) {
        if (!world.isClient) return
        if (world.time % Minecraft.TICKS != 0L) return
        particle(world, pos)
    }

    private fun particle(world: World, pos: BlockPos) {
        val color = AwesomeColors.green
        val offset = 0.5625 / 2
        val direction = Direction.values().random()
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
