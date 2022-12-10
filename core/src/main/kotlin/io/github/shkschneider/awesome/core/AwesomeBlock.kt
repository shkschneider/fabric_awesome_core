package io.github.shkschneider.awesome.core

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.ext.id
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockRenderType
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.loot.context.LootContext
import net.minecraft.loot.context.LootContextParameters
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

abstract class AwesomeBlock(
    val id: Identifier,
    settings: Settings,
    group: ItemGroup = Awesome.GROUP,
) : Block(settings) {

    private lateinit var _self: Block
    val self: Block get() = _self

    private lateinit var blockItem: BlockItem

    init {
        init(group)
    }

    private fun init(group: ItemGroup) {
        AwesomeRegistries.blockWithItem(id, this as Block, group).let {
            _self = it.first
            blockItem = it.second
        }
    }

    override fun asItem(): Item = blockItem

    @Suppress("OVERRIDE_DEPRECATION")
    override fun getRenderType(state: BlockState): BlockRenderType =
        BlockRenderType.MODEL

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState =
        defaultState

    @Deprecated("AwesomeBlockWithEntity")
    abstract class WithEntity<BE : BlockEntity>(
        id: Identifier,
        settings: Settings,
        group: ItemGroup = Awesome.GROUP,
    ) : AwesomeBlock(id, settings, group), BlockEntityProvider, BlockEntityTicker<BE> {

        val entityType: BlockEntityType<BE> = Registry.register(
            Registry.BLOCK_ENTITY_TYPE, id,
            FabricBlockEntityTypeBuilder.create({ pos, state -> createBlockEntity(pos, state) }, self).build(null)
        )

        abstract override fun createBlockEntity(pos: BlockPos, state: BlockState): BE

        override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hit: BlockHitResult): ActionResult {
            if (!world.isClient) state.createScreenHandlerFactory(world, pos)?.let(player::openHandledScreen)
            return ActionResult.SUCCESS
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : BlockEntity> getTicker(_world: World, _state: BlockState, type: BlockEntityType<T>): BlockEntityTicker<T> =
            BlockEntityTicker { world, pos, state, blockEntity ->
                // BEWARE: world.isClient
                tick(world, pos, state, blockEntity as BE)
            }

        interface RetainsInventory

        override fun onBreak(world: World, pos: BlockPos, state: BlockState, player: PlayerEntity) {
            val block = world.getBlockState(pos).block as? RetainsInventory
            val entity = world.getBlockEntity(pos) as? AwesomeBlockEntity.WithInventory
            if (entity != null && block != null) {
                if (!world.isClient && !player.isCreative) {
                    val stack = ItemStack(world.getBlockState(pos).block)
                    if (!entity.isEmpty) {
                        setBlockEntityTag(entity, stack)
                    }
                    world.spawnEntity(ItemEntity(world, pos.x + 0.5, pos.y + 0.5, pos.z + 0.5, stack).apply {
                        setToDefaultPickupDelay()
                    })
                }
            }
            super.onBreak(world, pos, state, player)
        }

        @Suppress("DEPRECATION")
        override fun getDroppedStacks(state: BlockState, builder: LootContext.Builder): MutableList<ItemStack> {
            val items = super.getDroppedStacks(state, builder)
            if (this is RetainsInventory) {
                for (stack in items) {
                    if (stack.item.id() == id) {
                        setBlockEntityTag(builder.get(LootContextParameters.BLOCK_ENTITY) as AwesomeBlockEntity.WithInventory, stack)
                    }
                }
            }
            return items
        }

        private fun setBlockEntityTag(crate: AwesomeBlockEntity.WithInventory, stack: ItemStack) {
            if (!crate.items.isEmpty()) {
                stack.setSubNbt(AwesomeUtils.BLOCK_ENTITY_TAG, NbtCompound().apply {
                    crate.writeNbt(this)
                })
            }
        }

        @Suppress("DEPRECATION")
        override fun onStateReplaced(state: BlockState, world: World, pos: BlockPos, newState: BlockState, moved: Boolean) {
            // do NOT ItemScatterer.spawn()
            super.onStateReplaced(state, world, pos, newState, moved)
        }

    }

    @Deprecated("AwesomeBlockWithEntity")
    abstract class WithScreen<BE : BlockEntity>(
        id: Identifier,
        settings: Settings,
        group: ItemGroup = Awesome.GROUP,
    ) : WithEntity<BE>(id, settings, group), BlockEntityProvider {

        /**
         * Block.onUse(): If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
         * @see net.minecraft.block.BlockWithEntity
         */
        override fun createScreenHandlerFactory(state: BlockState, world: World, pos: BlockPos): NamedScreenHandlerFactory? =
            world.getBlockEntity(pos) as? NamedScreenHandlerFactory

    }

}
