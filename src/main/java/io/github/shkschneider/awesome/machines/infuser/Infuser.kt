package io.github.shkschneider.awesome.machines.infuser

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

class Infuser : AwesomeMachine<InfuserBlock, InfuserBlock.Entity, InfuserScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    slots = SLOTS,
    blockProvider = {
        InfuserBlock(FabricBlockSettings.copyOf(Blocks.FURNACE).luminance(0))
    },
    blockEntityProvider = { pos, state ->
        InfuserBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        InfuserScreen(ID, handler, inventory, title)
    },
    screenHandlerProvider = { syncId, inventories, properties ->
        InfuserScreen.Handler(syncId, inventories, properties)
    },
) {

    companion object {

        const val ID = "infuser"
        val SLOTS = InputOutput.Slots(inputs = 2)
        val RECIPES = InfuserRecipes()

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, entity: InfuserBlock.Entity) {
        if (world.isClient()) return
        AwesomeMachineTicker(entity, SLOTS, RECIPES)(
            on = { entity.setPropertyState(Properties.LIT, true) },
            off = { entity.setPropertyState(Properties.LIT, false) },
        )
    }

}
