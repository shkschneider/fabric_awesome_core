package io.github.shkschneider.awesome.machines.recycler

import io.github.shkschneider.awesome.core.AwesomeLogger
import io.github.shkschneider.awesome.core.ext.id
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.recipe.RecipeType
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Recycler : AwesomeMachine<RecyclerBlock.Entity, RecyclerScreen.Handler>(
    id = "recycler",
    io = InputOutput(inputs = 1, outputs = 9),
) {

    companion object {

        private const val EFFICIENCY = 0.6 // much like GregTech's Disassembler

    }

    init {
        check(EFFICIENCY > 0.0 && EFFICIENCY <= 1.0)
    }

    override fun block(): AwesomeMachineBlock<RecyclerBlock.Entity, RecyclerScreen.Handler> =
        RecyclerBlock(this)

    override fun screenHandler(syncId: Int, playerInventory: PlayerInventory): RecyclerScreen.Handler =
        RecyclerScreen.Handler(this, null, syncId, playerInventory)

    override fun screen(handler: RecyclerScreen.Handler, playerInventory: PlayerInventory, title: Text): AwesomeMachineScreen<RecyclerBlock.Entity, RecyclerScreen.Handler> =
        RecyclerScreen(this, handler, playerInventory, title)

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: RecyclerBlock.Entity) {
        if (world.isClient) return
        val input = blockEntity.getInputSlot().second
        val outputs = blockEntity.getOutputSlots().map { it.second }
        if (input.isEmpty.not() && outputs.all { it.isEmpty }) {
            world.recipeManager.values().filter { it.type == RecipeType.CRAFTING }
                .filter { input.item == it.output.item && input.count >= it.output.count }
                .randomOrNull()?.let { recipe ->
                    val n = input.count / recipe.output.count // n >= 1
                    AwesomeLogger.debug("Recycler < $n*${recipe.output.item.id()}")
                    AwesomeLogger.debug("Recycler > ${recipe.ingredients.mapNotNull { it.matchingStacks.randomOrNull()?.item?.id() }}")
                    recipe.ingredients.mapNotNull { it.matchingStacks.randomOrNull()?.item }.forEachIndexed { index, item ->
                        val i = blockEntity.getOutputSlots()[index].first
                        if (world.random.nextBetween(0, 100) < (EFFICIENCY * 100)) {
                            blockEntity.setStack(i, ItemStack(item, n))
                        }
                    }
                    input.count -= recipe.output.count * n
                }
            blockEntity.markDirty()
        }
    }

}
