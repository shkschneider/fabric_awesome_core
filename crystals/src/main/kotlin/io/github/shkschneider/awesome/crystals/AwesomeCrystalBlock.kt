package io.github.shkschneider.awesome.crystals

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.custom.Minecraft
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.AmethystClusterBlock
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import net.minecraft.world.WorldView

class AwesomeCrystalBlock(
    protected val id: Identifier,
    protected val output: ItemStack,
) : AmethystClusterBlock(
    /* height */ 7, /* xzOffset */3,
    FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
) {

    init {
        init(id)
    }

    private fun init(id: Identifier) {
        AwesomeRegistries.blockWithItem(id, this, Awesome.GROUP)
        if (Minecraft.isClient) AwesomeRegistries.blockRenderer(this, RenderLayer.getCutout())
    }

    override fun onBreak(world: World, pos: BlockPos, state: BlockState, player: PlayerEntity) {
        super.onBreak(world, pos, state, player)
        if (!player.isCreative) dropStack(world, pos, output)
    }

    override fun getPistonBehavior(state: BlockState): PistonBehavior = PistonBehavior.NORMAL

    @Suppress("DEPRECATION")
    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean =
        super.canPlaceAt(state, world, pos) && world.getBlockState(pos.offset((state.get(Properties.FACING) as Direction).opposite)).block is AwesomeBuddingBlock

}
