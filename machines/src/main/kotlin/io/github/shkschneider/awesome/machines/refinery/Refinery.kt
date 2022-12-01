package io.github.shkschneider.awesome.machines.refinery

import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.MachinePorts
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineTicker
import io.github.shkschneider.awesome.AwesomeMachines
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Refinery : AwesomeMachine<RefineryBlock, RefineryBlock.Entity, RefineryScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    ports = PORTS,
    blockProvider = {
        RefineryBlock(FabricBlockSettings.copyOf(Blocks.FURNACE))
    },
    blockEntityProvider = { pos, state ->
        RefineryBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        RefineryScreen(ID, handler, inventory, title)
    },
    screenHandlerProvider = { syncId, sidedInventory, playerInventory, properties ->
        RefineryScreen.Handler(syncId, sidedInventory, playerInventory, properties)
    },
) {

    companion object {

        const val ID = "refinery"
        val PORTS = MachinePorts(inputs = 1, outputs = 1)
        val RECIPES = RefineryRecipes()

        init {
            check(RECIPES.all { it.inputs.size == PORTS.inputs.first })
        }

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: RefineryBlock.Entity) {
        if (world.isClient) return
        super.tick(world, pos, state, blockEntity)
        AwesomeMachineTicker(blockEntity, AwesomeMachines.refinery.ports, RECIPES)(world)
    }

}
