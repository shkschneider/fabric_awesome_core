package io.github.shkschneider.awesome.machines.refinery

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

class Refinery : AwesomeMachine<RefineryBlock.Entity, RefineryScreen.Handler>(
    id = "refinery",
    io = InputOutput(inputs = 1 to listOf(Faces.Top), outputs = 1 to listOf(Faces.Bottom)),
) {

    val recipes = RefineryRecipes()

    init {
        check(recipes.all { it.inputs.size == io.inputs.first })
    }

    override fun block(): AwesomeMachineBlock<RefineryBlock.Entity, RefineryScreen.Handler> =
        RefineryBlock(this)

    override fun screen(): ScreenHandlerType<RefineryScreen.Handler> =
        ScreenHandlerType { syncId, playerInventory ->
            RefineryScreen.Handler(syncId, SimpleSidedInventory(io.size), playerInventory, ArrayPropertyDelegate(properties))
        }.also {
            HandledScreens.register(it) { handler, playerInventory, title ->
                RefineryScreen(this, handler, playerInventory, title)
            }
        }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: RefineryBlock.Entity) {
        AwesomeMachineTicker(blockEntity, io, recipes)(world)
    }

}
