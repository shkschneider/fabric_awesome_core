package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.custom.MachinePorts
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineTicker
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Crusher : AwesomeMachine<CrusherBlock, CrusherBlock.Entity, CrusherScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    ports = PORTS,
    blockProvider = {
        CrusherBlock(FabricBlockSettings.copyOf(Blocks.FURNACE))
    },
    blockEntityProvider = { pos, state ->
        CrusherBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        CrusherScreen(ID, handler, inventory, title)
    },
    screenHandlerProvider = { syncId, sidedInventory, playerInventory, properties ->
        CrusherScreen.Handler(syncId, sidedInventory, playerInventory, properties)
    },
) {

    companion object {

        const val ID = "crusher"
        val PORTS = MachinePorts(inputs = 1, outputs = 1)
        val RECIPES = CrusherRecipes()

        init {
            check(RECIPES.all { it.inputs.size == PORTS.inputs.first })
        }

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: CrusherBlock.Entity) {
        if (world.isClient) return
        super.tick(world, pos, state, blockEntity)
        AwesomeMachineTicker(blockEntity, AwesomeMachines.crusher.ports, RECIPES)(world)
    }

}
