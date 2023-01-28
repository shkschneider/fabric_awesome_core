package io.github.shkschneider.awesome.machines.recycler

import io.github.shkschneider.awesome.core.AwesomeLogger
import io.github.shkschneider.awesome.core.ext.getStacks
import io.github.shkschneider.awesome.core.ext.id
import io.github.shkschneider.awesome.custom.Faces
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.custom.Minecraft
import io.github.shkschneider.awesome.custom.SimpleSidedInventory
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.inventory.SimpleInventory
import net.minecraft.recipe.RecipeType
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import kotlin.math.floor
import kotlin.math.roundToInt

object Recycler {

    const val ID = "recycler"
    val IO = InputOutput(inputs = 1 to listOf(Faces.Top), outputs = 9 to listOf(Faces.Bottom))
    const val PROPERTIES = 2
    private const val EFFICIENCY = 0.6 // much like GregTech's Disassembler

    init {
        check(EFFICIENCY > 0.0 && EFFICIENCY <= 1.0)
    }

    val block = RecyclerBlock(FabricBlockSettings.copyOf(Blocks.FURNACE))

    private lateinit var _screen: ScreenHandlerType<RecyclerScreenHandler>
    val screen get() = _screen

    operator fun invoke() {
        if (Minecraft.isClient) {
            _screen = ScreenHandlerType { syncId, playerInventory ->
                RecyclerScreenHandler(syncId, SimpleSidedInventory(IO.size), playerInventory, ArrayPropertyDelegate(
                    PROPERTIES
                ))
            }
            HandledScreens.register(_screen) { handler, playerInventory, title ->
                RecyclerScreen(ID, handler, playerInventory, title)
            }
        }
    }

    fun tick(world: World, pos: BlockPos, state: BlockState, entity: RecyclerBlockEntity) {
        if (world.isClient) return
        val input = entity.getStacks().take(IO.inputs.first).first()
        val outputs = entity.getStacks().drop(IO.inputs.first)
        if (input.isEmpty.not() && outputs.all { it.isEmpty }) {
            world.recipeManager.values().filter { it.type == RecipeType.CRAFTING }
                .filter { input.item == it.output.item && input.count >= it.output.count }
                .randomOrNull()?.let { recipe ->
                    val n = input.count / recipe.output.count
                    AwesomeLogger.debug("Recycler: ${recipe.output.item.id()}*$n")
                    SimpleInventory(IO.outputs.first).apply {
                        recipe.ingredients.map { it.matchingStacks.first() }.forEach { ingredient ->
                            addStack(ingredient)
                        }
                    }.apply {
                        getStacks().forEachIndexed { index, itemStack ->
                            entity.setStack(1 + index, itemStack.apply {
                                count = floor((count * n) * EFFICIENCY).roundToInt()
                            })
                        }
                    }
                    input.count -= recipe.output.count * n
                }
            entity.markDirty()
        }
    }

}
