package io.github.shkschneider.awesome.machines.infuser

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

    override fun screenHandler(syncId: Int, playerInventory: PlayerInventory): InfuserScreen.Handler =
        InfuserScreen.Handler(this, null, syncId, playerInventory)

    override fun screen(handler: InfuserScreen.Handler, playerInventory: PlayerInventory, title: Text): AwesomeMachineScreen<InfuserBlock.Entity, InfuserScreen.Handler> =
        InfuserScreen(this, handler, playerInventory, title)

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: InfuserBlock.Entity) {
        AwesomeMachineTicker(blockEntity, io, recipes)(world)
    }

}
