package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.block.BlockState
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView
import kotlin.math.roundToInt

class GeneratorBlock(settings: Settings) : AwesomeMachineBlock<GeneratorBlock.Entity>(
    settings,
    entityTypeProvider = { AwesomeMachines.generator.entityType },
    blockEntityProvider = { pos, state -> Entity(pos, state) },
    tickerProvider = { AwesomeMachines.generator },
) {

    override fun emitsRedstonePower(state: BlockState): Boolean = true

    override fun getStrongRedstonePower(state: BlockState, world: BlockView, pos: BlockPos, direction: Direction): Int =
        Properties.POWER.values.max()

    override fun getWeakRedstonePower(state: BlockState, world: BlockView, pos: BlockPos, direction: Direction): Int =
        Properties.POWER.values.average().roundToInt()

    class Entity(pos: BlockPos, state: BlockState) : AwesomeMachineBlockEntity(
        Generator.ID, AwesomeMachines.generator.entityType,
        pos, state, Generator.SLOTS, emptyList(),
        screenHandlerProvider = { syncId, sidedInventory, playerInventory, properties ->
            GeneratorScreen.Handler(syncId, sidedInventory, playerInventory, properties)
        },
    )

}

