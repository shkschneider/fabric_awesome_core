package io.github.shkschneider.awesome.machines.crafter

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachine
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Crafter : AwesomeMachine<CrafterBlock, CrafterBlock.Entity, CrafterScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    slots = SLOTS,
    blockProvider = {
        CrafterBlock(FabricBlockSettings.copyOf(Blocks.CRAFTING_TABLE))
    },
    blockEntityProvider = { pos, state ->
        CrafterBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        CrafterScreen(ID, handler, inventory, title)
    },
    screenHandlerProvider = { syncId, inventories, properties ->
        CrafterScreen.Handler(syncId, inventories, properties)
    },
) {

    companion object {

        const val ID = "crafter"
        val SLOTS = InputOutput.Slots(inputs = 9)

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, entity: CrafterBlock.Entity) {
        if (world.isClient()) return
        // TODO
    }

}
