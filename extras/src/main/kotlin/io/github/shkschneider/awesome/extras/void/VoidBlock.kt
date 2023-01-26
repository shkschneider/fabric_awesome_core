package io.github.shkschneider.awesome.extras.void

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeBlockWithEntity
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class VoidBlock : AwesomeBlockWithEntity<VoidBlockEntity>(
    id = AwesomeUtils.identifier(Void.ID),
    settings = FabricBlockSettings.copyOf(Blocks.BONE_BLOCK)
        .noBlockBreakParticles().requiresTool()
        .sounds(BlockSoundGroup.GLASS)
        .allowsSpawning { _, _, _, _ -> false },
    group = Awesome.GROUP,
) {

    // FIXME cannot place one on top of another

    override fun createBlockEntity(pos: BlockPos, state: BlockState): VoidBlockEntity = VoidBlockEntity(entityType, pos, state)

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: VoidBlockEntity) {}

    override fun getPistonBehavior(state: BlockState): PistonBehavior = PistonBehavior.DESTROY

}
