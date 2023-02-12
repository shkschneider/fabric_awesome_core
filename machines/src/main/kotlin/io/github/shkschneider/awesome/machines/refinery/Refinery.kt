package io.github.shkschneider.awesome.machines.refinery

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

class Refinery : AwesomeMachine<RefineryBlock.Entity, RefineryScreen.Handler>(
    id = "refinery",
    io = InputOutput(inputs = 1, outputs = 1),
) {

    val recipes = RefineryRecipes()

    init {
        check(recipes.all { it.inputs.size == io.inputs })
    }

    override fun block(): AwesomeMachineBlock<RefineryBlock.Entity, RefineryScreen.Handler> =
        RefineryBlock(this)

    override fun screenHandler(syncId: Int, playerInventory: PlayerInventory): RefineryScreen.Handler =
        RefineryScreen.Handler(this, null, syncId, playerInventory)

    override fun screen(handler: RefineryScreen.Handler, playerInventory: PlayerInventory, title: Text): AwesomeMachineScreen<RefineryBlock.Entity, RefineryScreen.Handler> =
        RefineryScreen(this, handler, playerInventory, title)

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: RefineryBlock.Entity) {
        AwesomeMachineTicker(blockEntity, io, recipes)(world)
    }

}
