package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos

class CrusherBlock(settings: Settings) : AwesomeMachineBlock<CrusherBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.crusher.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.crusher },
) {

    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Crusher.ID, AwesomeMachines.crusher.entityType,
        pos, state, Crusher.SLOTS, Crusher.RECIPES,
        screenHandlerProvider = { syncId, inventories, properties ->
            CrusherScreen.Handler(syncId, inventories, properties)
        },
    )

}
