package io.github.shkschneider.awesome.core

import io.github.shkschneider.awesome.Awesome
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemPlacementContext
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

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
        _block = AwesomeRegistries.block(id, this as Block, group)
    }

    @Suppress("OVERRIDE_DEPRECATION")
    override fun getRenderType(state: BlockState): BlockRenderType =
        BlockRenderType.MODEL

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState =
        defaultState

    abstract class WithEntity<BE : BlockEntity>(
        id: Identifier,
        settings: Settings,
        group: ItemGroup = Awesome.GROUP,
    ) : AwesomeBlock(id, settings, group), BlockEntityProvider, BlockEntityTicker<BE> {

        val entityType: BlockEntityType<BE> = Registry.register(
            Registry.BLOCK_ENTITY_TYPE, id,
            FabricBlockEntityTypeBuilder.create({ pos, state -> createBlockEntity(pos, state) }, block).build(null)
        )

        abstract override fun createBlockEntity(pos: BlockPos, state: BlockState): BE

        @Suppress("UNCHECKED_CAST")
        override fun <T : BlockEntity> getTicker(world: World, state: BlockState, type: BlockEntityType<T>): BlockEntityTicker<T> =
            this as BlockEntityTicker<T>

    }

    abstract class WithScreen<BE : BlockEntity>(
        id: Identifier,
        settings: Settings,
        group: ItemGroup = Awesome.GROUP,
    ) : WithEntity<BE>(id, settings, group), BlockEntityProvider, BlockEntityTicker<BE> {

        /**
         * Block.onUse(): If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
         * @see net.minecraft.block.BlockWithEntity
         */
        override fun createScreenHandlerFactory(state: BlockState, world: World, pos: BlockPos): NamedScreenHandlerFactory? =
            world.getBlockEntity(pos) as? NamedScreenHandlerFactory

    }

}
