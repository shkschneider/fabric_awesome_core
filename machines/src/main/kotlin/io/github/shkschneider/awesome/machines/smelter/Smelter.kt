package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.MachinePorts
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineTicker
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Smelter : AwesomeMachine<SmelterBlock, SmelterBlock.Entity, SmelterScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    ports = PORTS,
    blockProvider = {
        SmelterBlock(FabricBlockSettings.copyOf(Blocks.FURNACE))
    },
    blockEntityProvider = { pos, state ->
        SmelterBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        SmelterScreen(ID, handler, inventory, title)
    },
    screenHandlerProvider = { syncId, sidedInventory, playerInventory, properties ->
        SmelterScreen.Handler(syncId, sidedInventory, playerInventory, properties)
    },
) {

    companion object {

        const val ID = "smelter"
        val PORTS = MachinePorts(inputs = 1, outputs = 1)
        val RECIPES = SmelterRecipes()

        init {
            check(RECIPES.all { it.inputs.size == PORTS.inputs.first })
        }

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: SmelterBlock.Entity) {
        if (world.isClient) return
        super.tick(world, pos, state, blockEntity)
        AwesomeMachineTicker(blockEntity, ports, RECIPES)(world)
    }

}
