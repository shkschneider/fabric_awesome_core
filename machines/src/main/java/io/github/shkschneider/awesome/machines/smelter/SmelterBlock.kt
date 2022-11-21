package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.AwesomeMachines
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos

class SmelterBlock(settings: Settings) : AwesomeMachineBlock<SmelterBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.smelter.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.smelter },
) {

    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Smelter.ID, AwesomeMachines.smelter.entityType,
        pos, state, AwesomeMachines.smelter.ports, Smelter.RECIPES,
        screenHandlerProvider = { syncId, sidedInventory, playerInventory, properties ->
            SmelterScreen.Handler(syncId, sidedInventory, playerInventory, properties)
        },
    )

}
