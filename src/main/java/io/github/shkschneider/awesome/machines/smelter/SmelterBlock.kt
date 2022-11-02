package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.math.BlockPos

class SmelterBlock(settings: Settings) : AwesomeMachineBlock<SmelterBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.smelter.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.smelter },
) {

    @Suppress("USELESS_CAST")
    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Smelter.ID, AwesomeMachines.smelter.entityType as BlockEntityType<Entity>,
        pos, state,
        Smelter.SLOTS.first + Smelter.SLOTS.second,
        screenHandlerProvider = { syncId, playerInventory, inventory, properties ->
            SmelterScreen.Handler(syncId, playerInventory, inventory, properties)
        },
    )

}
