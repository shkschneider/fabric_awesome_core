package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos

class GeneratorBlock(settings: Settings) : AwesomeMachineBlock<GeneratorBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.generator.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.generator },
) {

    override fun emitsRedstonePower(state: BlockState): Boolean = state.get(Properties.LIT)

    @Suppress("USELESS_CAST")
    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Generator.ID, AwesomeMachines.generator.entityType as BlockEntityType<Entity>,
        pos, state, Generator.SLOTS, emptyList(),
        screenHandlerProvider = { syncId, inventories, properties ->
            GeneratorScreen.Handler(syncId, inventories, properties)
        },
    )

}

