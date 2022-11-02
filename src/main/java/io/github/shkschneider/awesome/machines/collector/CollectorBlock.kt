package io.github.shkschneider.awesome.machines.collector

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

class CollectorBlock(settings: Settings) : AwesomeMachineBlock<CollectorBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.collector.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.collector },
) {

    @Suppress("USELESS_CAST")
    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Collector.ID, AwesomeMachines.collector.entityType as BlockEntityType<Entity>,
        pos, state,
        Collector.SLOTS,
        screenHandlerProvider = { syncId, inventories, properties ->
            CollectorScreen.Handler(syncId, inventories, properties)
        },
        canInsert = emptyList(),
        canExtract = listOf(Direction.DOWN to true),
    )

}
