package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.core.AwesomeBlockWithScreen
import io.github.shkschneider.awesome.custom.InputOutput
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.inventory.Inventory
import net.minecraft.screen.slot.Slot
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction

class MachineTest : AwesomeBlockWithScreen(
    id = AwesomeUtils.identifier("machine_test"),
    settings = FabricBlockSettings.copyOf(Blocks.DIRT),
    slots = InputOutput.Slots(inputs = 1, fuel = null, outputs = 1),
) {

    companion object {

        const val PROGRESS = 0

    }

    override val properties: Map<Int, Int> = mapOf(
        PROGRESS to 0,
    )

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.FACING).add(Properties.LIT)
    }

    override fun initState(state: BlockState): BlockState = state
        .with(Properties.FACING, Direction.NORTH)
        .with(Properties.LIT, false)

    override fun getTexture(): Identifier = AwesomeUtils.identifier("textures/gui/crafter.png")

    override fun initSlots(inventory: Inventory): Pair<List<Slot>, Boolean> = emptyList<Slot>() to true

}
