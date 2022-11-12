package io.github.shkschneider.awesome.machines.collector

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos

class CollectorBlock(settings: Settings) : AwesomeMachineBlock<CollectorBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.collector.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.collector },
) {

    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Collector.ID, AwesomeMachines.collector.entityType,
        pos, state, Collector.SLOTS, emptyList(),
        screenHandlerProvider = { syncId, inventories, properties ->
            CollectorScreen.Handler(syncId, inventories, properties)
        },
    )

}

