package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.Faces
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineTicker
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Crusher : AwesomeMachine<CrusherBlock, CrusherBlock.Entity, CrusherScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    io = IO,
    blockProvider = {
        CrusherBlock(FabricBlockSettings.copyOf(Blocks.FURNACE))
    },
    blockEntityProvider = { pos, state ->
        CrusherBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        CrusherScreen(ID, handler, inventory, title)
    },
    screenHandlerProvider = { syncId, sidedInventory, playerInventory, properties ->
        CrusherScreen.Handler(syncId, sidedInventory, playerInventory, properties)
    },
) {

    companion object {

        const val ID = "crusher"
        val IO = InputOutput(inputs = 1 to listOf(Faces.Top), outputs = 1 to listOf(Faces.Bottom))
        val RECIPES = CrusherRecipes()

        init {
            check(RECIPES.all { it.inputs.size == IO.inputs.first })
        }

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: CrusherBlock.Entity) {
        if (world.isClient) return
        super.tick(world, pos, state, blockEntity)
        AwesomeMachineTicker(blockEntity, AwesomeMachines.crusher.io, RECIPES)(world)
    }

}
