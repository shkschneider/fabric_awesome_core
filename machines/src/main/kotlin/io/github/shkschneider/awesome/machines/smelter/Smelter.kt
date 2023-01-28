package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.custom.Faces
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

class Smelter : AwesomeMachine<SmelterBlock.Entity, SmelterScreen.Handler>(
    id = "smelter",
    io = InputOutput(inputs = 1 to listOf(Faces.Top), outputs = 1 to listOf(Faces.Bottom)),
) {

    val recipes = SmelterRecipes()

    init {
        check(recipes.all { it.inputs.size == io.inputs.first })
    }

    override fun block(): AwesomeMachineBlock<SmelterBlock.Entity, SmelterScreen.Handler> =
        SmelterBlock(this)

    override fun screen(): ScreenHandlerType<SmelterScreen.Handler> =
        ScreenHandlerType { syncId, playerInventory ->
            SmelterScreen.Handler(syncId, SimpleSidedInventory(io.size), playerInventory, ArrayPropertyDelegate(properties))
        }.also {
            HandledScreens.register(it) { handler, playerInventory, title ->
                SmelterScreen(this, handler, playerInventory, title)
            }
        }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: SmelterBlock.Entity) {
        AwesomeMachineTicker(blockEntity, io, recipes)(world)
    }

}
