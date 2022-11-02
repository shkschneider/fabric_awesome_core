package io.github.shkschneider.awesome.machines.refinery

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.math.BlockPos

class RefineryBlock(settings: Settings) : AwesomeMachineBlock<RefineryBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.refinery.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.refinery },
) {

    @Suppress("USELESS_CAST")
    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Refinery.ID, AwesomeMachines.refinery.entityType as BlockEntityType<Entity>,
        pos, state,
        Refinery.SLOTS,
        screenHandlerProvider = { syncId, inventories, properties ->
            RefineryScreen.Handler(syncId, inventories, properties)
        },
    )

}
