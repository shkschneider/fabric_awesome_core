package io.github.shkschneider.awesome.machines.crusher

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

class Crusher : AwesomeMachine<CrusherBlock.Entity, CrusherScreen.Handler>(
    id = "crusher",
    io = InputOutput(inputs = 1, outputs = 1),
) {

    val recipes = CrusherRecipes()

    init {
        check(recipes.all { it.inputs.size == io.inputs })
    }

    override fun block(): AwesomeMachineBlock<CrusherBlock.Entity, CrusherScreen.Handler> =
        CrusherBlock(this)

    override fun screenHandler(syncId: Int, playerInventory: PlayerInventory): CrusherScreen.Handler =
        CrusherScreen.Handler(this, null, syncId, playerInventory)

    override fun screen(handler: CrusherScreen.Handler, playerInventory: PlayerInventory, title: Text): AwesomeMachineScreen<CrusherBlock.Entity, CrusherScreen.Handler> =
        CrusherScreen(this, handler, playerInventory, title)

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: CrusherBlock.Entity) {
        AwesomeMachineTicker(blockEntity, io, recipes)(world)
    }

}
