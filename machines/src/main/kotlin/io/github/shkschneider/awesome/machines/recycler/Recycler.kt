package io.github.shkschneider.awesome.machines.recycler

import io.github.shkschneider.awesome.core.AwesomeLogger
import io.github.shkschneider.awesome.core.ext.getStacks
import io.github.shkschneider.awesome.core.ext.id
import io.github.shkschneider.awesome.custom.Faces
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.custom.SimpleSidedInventory
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import net.minecraft.block.BlockState
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.recipe.RecipeType
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Recycler : AwesomeMachine<RecyclerBlock.Entity, RecyclerScreen.Handler>(
    id = "recycler",
    io = InputOutput(inputs = 1 to listOf(Faces.Top), outputs = 9 to listOf(Faces.Bottom)),
) {

    companion object {

        private const val EFFICIENCY = 0.6 // much like GregTech's Disassembler

    }

    init {
        check(EFFICIENCY > 0.0 && EFFICIENCY <= 1.0)
    }

    override fun block(): AwesomeMachineBlock<RecyclerBlock.Entity, RecyclerScreen.Handler> =
        RecyclerBlock(this)

    override fun screen(): ScreenHandlerType<RecyclerScreen.Handler> =
        ScreenHandlerType { syncId, playerInventory ->
            RecyclerScreen.Handler(syncId, SimpleSidedInventory(io.size), playerInventory, ArrayPropertyDelegate(properties))
        }.also {
            HandledScreens.register(it) { handler, playerInventory, title ->
                RecyclerScreen(this, handler, playerInventory, title)
            }
        }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: RecyclerBlock.Entity) {
        val input = blockEntity.getStacks().take(io.inputs.first).first()
        val outputs = blockEntity.getStacks().drop(io.inputs.first)
        if (input.isEmpty.not() && outputs.all { it.isEmpty }) {
            world.recipeManager.values().filter { it.type == RecipeType.CRAFTING }
                .filter { input.item == it.output.item && input.count >= it.output.count }
                .randomOrNull()?.let { recipe ->
                    val n = input.count / recipe.output.count
                    AwesomeLogger.debug("Recycler: ${recipe.output.item.id()}*$n")
//                    SimpleInventory(io.outputs.first).apply {
//                        // FIXME Array is empty.
//                        recipe.ingredients.map { it.matchingStacks.first() }.forEach { ingredient ->
//                            addStack(ingredient)
//                        }
//                    }.apply {
//                        getStacks().forEachIndexed { index, itemStack ->
//                            blockEntity.setStack(1 + index, itemStack.apply {
//                                count = floor((count * n) * EFFICIENCY).roundToInt()
//                            })
//                        }
//                    }
//                    input.count -= recipe.output.count * n
                }
            blockEntity.markDirty()
        }
    }

}
