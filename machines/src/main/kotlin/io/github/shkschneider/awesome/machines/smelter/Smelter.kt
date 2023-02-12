package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineTicker
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Smelter : AwesomeMachine<SmelterBlock.Entity, SmelterScreen.Handler>(
    id = "smelter",
    io = InputOutput(inputs = 1, fueled = true, outputs = 1),
) {

    val recipes = SmelterRecipes()

    init {
        check(recipes.all { it.inputs.none { it.item == AwesomeMachines.fuel } })
    }

    override fun block(): AwesomeMachineBlock<SmelterBlock.Entity, SmelterScreen.Handler> =
        SmelterBlock(this)

    override fun screenHandler(syncId: Int, playerInventory: PlayerInventory): SmelterScreen.Handler =
        SmelterScreen.Handler(this, null, syncId, playerInventory)

    override fun screen(handler: SmelterScreen.Handler, playerInventory: PlayerInventory, title: Text): AwesomeMachineScreen<SmelterBlock.Entity, SmelterScreen.Handler> =
        SmelterScreen(this, handler, playerInventory, title)

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: SmelterBlock.Entity) {
        AwesomeMachineTicker(blockEntity, io, recipes)(world)
    }

}
