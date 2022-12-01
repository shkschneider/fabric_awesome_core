package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import net.minecraft.block.BlockState
import net.minecraft.util.math.BlockPos

class InfuserBlock(settings: Settings) : AwesomeMachineBlock<InfuserBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.infuser.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.infuser },
) {

    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Infuser.ID, AwesomeMachines.infuser.entityType,
        pos, state, AwesomeMachines.infuser.ports, Infuser.RECIPES,
        screenHandlerProvider = { syncId, blockEntity, playerInventory, properties ->
            InfuserScreen.Handler(syncId, blockEntity, playerInventory, properties)
        },
    )

}
