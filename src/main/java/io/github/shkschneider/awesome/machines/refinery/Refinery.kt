package io.github.shkschneider.awesome.machines.refinery

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineTicker
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Refinery : AwesomeMachine<RefineryBlock, RefineryBlock.Entity, RefineryScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    slots = SLOTS,
    blockProvider = {
        RefineryBlock(FabricBlockSettings.copyOf(Blocks.FURNACE).luminance(0))
    },
    blockEntityProvider = { pos, state ->
        RefineryBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        RefineryScreen(handler, inventory, title)
    },
    screenHandlerProvider = { syncId, inventories, properties ->
        RefineryScreen.Handler(syncId, inventories, properties)
    },
) {

    companion object {

        const val ID = "refinery"
        val SLOTS = InputOutput.Slots(inputs = 1)

    }

    init {
        RefineryRecipes()
    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, entity: RefineryBlock.Entity) {
        if (world.isClient()) return
        AwesomeMachineTicker(entity, SLOTS, RefineryRecipes())(
            on = { entity.setPropertyState(Properties.LIT, true) },
            off = { entity.setPropertyState(Properties.LIT, false) },
        )
    }

}
