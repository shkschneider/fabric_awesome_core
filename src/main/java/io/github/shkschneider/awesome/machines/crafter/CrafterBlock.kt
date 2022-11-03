package io.github.shkschneider.awesome.machines.crafter

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.math.BlockPos

class CrafterBlock(settings: Settings) : AwesomeMachineBlock<CrafterBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.crafter.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.crafter },
) {

    @Suppress("USELESS_CAST")
    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Crafter.ID, AwesomeMachines.crafter.entityType as BlockEntityType<Entity>,
        pos, state, Crafter.SLOTS, emptyList(),
        screenHandlerProvider = { syncId, inventories, properties ->
            CrafterScreen.Handler(syncId, inventories, properties)
        },
    )

}

