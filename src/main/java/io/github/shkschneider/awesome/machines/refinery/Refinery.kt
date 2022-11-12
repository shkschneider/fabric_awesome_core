package io.github.shkschneider.awesome.machines.refinery

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineTicker
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Refinery : AwesomeMachine<RefineryBlock, RefineryBlock.Entity, RefineryScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    slots = SLOTS,
    blockProvider = {
        RefineryBlock(FabricBlockSettings.copyOf(Blocks.FURNACE))
    },
    blockEntityProvider = { pos, state ->
        RefineryBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        RefineryScreen(ID, handler, inventory, title)
    },
    screenHandlerProvider = { syncId, inventories, properties ->
        RefineryScreen.Handler(syncId, inventories, properties)
    },
) {

    companion object {

        const val ID = "refinery"
        val SLOTS = InputOutput.Slots(inputs = 1, outputs = 1)
        val RECIPES = RefineryRecipes()

        init {
            check(RECIPES.all { it.inputs.size == SLOTS.inputs })
        }

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: RefineryBlock.Entity) {
        if (world.isClient) return
        super.tick(world, pos, state, blockEntity)
        AwesomeMachineTicker(blockEntity, SLOTS, RECIPES)(world)
    }

}
