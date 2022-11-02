package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.math.BlockPos

class CrusherBlock(settings: Settings) : AwesomeMachineBlock<CrusherBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.crusher.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.crusher },
) {

    @Suppress("USELESS_CAST")
    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Crusher.ID, AwesomeMachines.crusher.entityType as BlockEntityType<Entity>,
        pos, state,
        Crusher.SLOTS,
        screenHandlerProvider = { syncId, inventories, properties ->
            CrusherScreen.Handler(syncId, inventories, properties)
        },
    )

}
