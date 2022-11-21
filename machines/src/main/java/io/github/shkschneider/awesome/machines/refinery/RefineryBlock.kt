package io.github.shkschneider.awesome.machines.refinery

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.AwesomeMachines
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
        pos, state, AwesomeMachines.refinery.ports, Refinery.RECIPES,
        screenHandlerProvider = { syncId, sidedInventory, playerInventory, properties ->
            RefineryScreen.Handler(syncId, sidedInventory, playerInventory, properties)
        },
    )

}
