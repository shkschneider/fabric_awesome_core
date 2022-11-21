package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.MachinePorts
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineTicker
import io.github.shkschneider.awesome.AwesomeMachines
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Infuser : AwesomeMachine<InfuserBlock, InfuserBlock.Entity, InfuserScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    ports = PORTS,
    blockProvider = {
        InfuserBlock(FabricBlockSettings.copyOf(Blocks.FURNACE))
    },
    blockEntityProvider = { pos, state ->
        InfuserBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        InfuserScreen(ID, handler, inventory, title)
    },
    screenHandlerProvider = { syncId, sidedInventory, playerInventory, properties ->
        InfuserScreen.Handler(syncId, sidedInventory, playerInventory, properties)
    },
) {

    companion object {

        const val ID = "infuser"
        val PORTS = MachinePorts(inputs = 2, outputs = 1)
        val RECIPES = InfuserRecipes()

        init {
            check(RECIPES.all { it.inputs.size == PORTS.inputs.first })
        }

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: InfuserBlock.Entity) {
        if (world.isClient) return
        super.tick(world, pos, state, blockEntity)
        AwesomeMachineTicker(blockEntity, AwesomeMachines.infuser.ports, RECIPES)(world)
    }

}
