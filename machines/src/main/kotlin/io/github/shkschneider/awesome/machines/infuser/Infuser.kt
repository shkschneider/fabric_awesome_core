package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.custom.SimpleSidedInventory
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineTicker
import net.minecraft.block.BlockState
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Infuser : AwesomeMachine<InfuserBlock.Entity, InfuserScreen.Handler>(
    id = "infuser",
    io = InputOutput(inputs = 2, outputs = 1),
) {

    val recipes = InfuserRecipes()

    init {
        check(recipes.all { it.inputs.size == io.inputs })
    }

    override fun block(): AwesomeMachineBlock<InfuserBlock.Entity, InfuserScreen.Handler> =
        InfuserBlock(this)

    override fun screen(): ScreenHandlerType<InfuserScreen.Handler> =
        ScreenHandlerType { syncId, playerInventory ->
            InfuserScreen.Handler(syncId, SimpleSidedInventory(io.size), playerInventory, ArrayPropertyDelegate(properties))
        }.also {
            HandledScreens.register(it) { handler, playerInventory, title ->
                InfuserScreen(this, handler, playerInventory, title)
            }
        }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: InfuserBlock.Entity) {
        AwesomeMachineTicker(blockEntity, io, recipes)(world)
    }

}
