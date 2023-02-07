package io.github.shkschneider.awesome.crystals

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.custom.Minecraft
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.AmethystClusterBlock
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class AwesomeCrystalBlock(
    protected val id: Identifier,
) : AmethystClusterBlock(
    /* height */ 7, /* xzOffset */3,
    FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)
) {

    init {
        init(id)
    }

    private fun init(id: Identifier) {
        AwesomeRegistries.blockItem(id, this, Awesome.GROUP)
        if (Minecraft.isClient) {
            BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), this)
        }
    }

    override fun getPistonBehavior(state: BlockState): PistonBehavior = PistonBehavior.NORMAL

}
