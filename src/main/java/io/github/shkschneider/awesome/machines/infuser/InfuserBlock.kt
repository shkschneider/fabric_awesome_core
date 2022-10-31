package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.math.BlockPos

class InfuserBlock(settings: Settings) : AwesomeMachineBlock<InfuserBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.infuser.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.infuser },
) {

    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Infuser.ID, AwesomeMachines.infuser.entityType as BlockEntityType<InfuserBlock.Entity>,
        pos, state,
        Infuser.SLOTS.first + Infuser.SLOTS.second,
        screenHandlerProvider = { syncId, playerInventory, inventory, properties ->
            InfuserScreen.Handler(syncId, playerInventory, inventory, properties)
        },
    )

}