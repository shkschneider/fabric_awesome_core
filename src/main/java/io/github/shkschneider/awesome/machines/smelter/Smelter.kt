package io.github.shkschneider.awesome.machines.smelter

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

class Smelter : AwesomeMachine<SmelterBlock, SmelterBlock.Entity, SmelterScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    slots = SLOTS,
    blockProvider = {
        SmelterBlock(FabricBlockSettings.copyOf(Blocks.FURNACE).luminance(0))
    },
    blockEntityProvider = { pos, state ->
        SmelterBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        SmelterScreen(handler, inventory, title)
    },
    screenHandlerProvider = { syncId, inventories, properties ->
        SmelterScreen.Handler(syncId, inventories, properties)
    },
) {

    companion object {

        const val ID = "smelter"
        val SLOTS = InputOutput.Slots(inputs = 1)

    }

    init {
        SmelterRecipes()
    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, entity: SmelterBlock.Entity) {
        if (world.isClient()) return
        AwesomeMachineTicker(entity, SLOTS, SmelterRecipes())(
            on = { entity.setPropertyState(Properties.LIT, true) },
            off = { entity.setPropertyState(Properties.LIT, false) },
        )
    }

}
