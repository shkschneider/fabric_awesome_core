package io.github.shkschneider.awesome.machines.crusher

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

class Crusher : AwesomeMachine<CrusherBlock.Entity, CrusherScreen.Handler>(
    id = "crusher",
    io = InputOutput(inputs = 1 to listOf(Faces.Top), outputs = 1 to listOf(Faces.Bottom)),
) {

    val recipes = CrusherRecipes()

    init {
        check(recipes.all { it.inputs.size == io.inputs.first })
    }

    override fun block(): AwesomeMachineBlock<CrusherBlock.Entity, CrusherScreen.Handler> =
        CrusherBlock(this)

    override fun screen(): ScreenHandlerType<CrusherScreen.Handler> =
        ScreenHandlerType { syncId, playerInventory ->
            CrusherScreen.Handler(syncId, SimpleSidedInventory(io.size), playerInventory, ArrayPropertyDelegate(properties))
        }.also {
            HandledScreens.register(it) { handler, playerInventory, title ->
                CrusherScreen(this, handler, playerInventory, title)
            }
        }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: CrusherBlock.Entity) {
        AwesomeMachineTicker(blockEntity, io, recipes)(world)
    }

}
