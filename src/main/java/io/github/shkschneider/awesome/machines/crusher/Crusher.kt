package io.github.shkschneider.awesome.machines.crusher

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

class Crusher : AwesomeMachine<CrusherBlock, CrusherBlock.Entity, CrusherScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    slots = SLOTS,
    blockProvider = {
        CrusherBlock(FabricBlockSettings.copyOf(Blocks.FURNACE).luminance(0))
    },
    blockEntityProvider = { pos, state ->
        CrusherBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        CrusherScreen(ID, handler, inventory, title)
    },
    screenHandlerProvider = { syncId, inventories, properties ->
        CrusherScreen.Handler(syncId, inventories, properties)
    },
) {

    companion object {

        const val ID = "crusher"
        val SLOTS = InputOutput.Slots(inputs = 1)
        val RECIPES = CrusherRecipes()

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, entity: CrusherBlock.Entity) {
        if (world.isClient()) return
        AwesomeMachineTicker(entity, SLOTS, RECIPES)(
            on = { entity.setPropertyState(Properties.LIT, true) },
            off = { entity.setPropertyState(Properties.LIT, false) },
        )
    }

}
