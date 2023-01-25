package io.github.shkschneider.awesome.extras.void

import io.github.shkschneider.awesome.custom.Minecraft
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories

object Void {

    const val ID = "void"

    operator fun invoke() {
        val block = VoidBlock()
        if (Minecraft.isClient) {
            BlockEntityRendererFactories.register(block.entityType) { VoidBlockEntityRenderer(it) }
        }
    }

}
