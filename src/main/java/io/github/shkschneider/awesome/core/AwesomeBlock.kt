package io.github.shkschneider.awesome.core

import io.github.shkschneider.awesome.Awesome
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemPlacementContext
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry

abstract class AwesomeBlock(
    val id: Identifier,
    settings: Settings,
    group: ItemGroup = Awesome.GROUP,
) : Block(settings) {

    private lateinit var _block: Block
    val block: Block get() = _block

    init {
        init(group)
    }

    private fun init(group: ItemGroup) {
        AwesomeRegistries.block(id, this as Block, group)
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.MODEL
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState =
        defaultState

    abstract class WithEntity(
        id: Identifier,
        settings: Settings,
    ) : AwesomeBlock(id, settings), BlockEntityProvider {

        val entityType: BlockEntityType<out BlockEntity> = Registry.register(
            Registry.BLOCK_ENTITY_TYPE, id,
            FabricBlockEntityTypeBuilder.create({ pos, state -> createBlockEntity(pos, state) }, block).build(null)
        )

        abstract override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity

    }

}
