package io.github.shkschneider.awesome.machines.refinery

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos

class RefineryBlock(settings: Settings) : AwesomeMachineBlock<RefineryBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.refinery.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.refinery },
) {

    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Refinery.ID, AwesomeMachines.refinery.entityType,
        pos, state, Refinery.SLOTS, Refinery.RECIPES,
        screenHandlerProvider = { syncId, inventories, properties ->
            RefineryScreen.Handler(syncId, inventories, properties)
        },
    )

}
