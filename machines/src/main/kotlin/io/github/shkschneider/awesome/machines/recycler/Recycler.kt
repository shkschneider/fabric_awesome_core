package io.github.shkschneider.awesome.machines.recycler

import io.github.shkschneider.awesome.core.AwesomeLogger
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.ext.getStacks
import io.github.shkschneider.awesome.core.ext.id
import io.github.shkschneider.awesome.custom.MachinePorts
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

    const val NAME = "recycler"
    val ID = AwesomeUtils.identifier(NAME)
    val PORTS = MachinePorts(inputs = 1, outputs = 9)
    val PROPERTIES = 2
    const val EFFICIENCY = 0.6 // much like GregTech's Disassembler

    init {
        check(EFFICIENCY > 0.0 && EFFICIENCY <= 1.0)
    }

    fun tick(world: World, pos: BlockPos, state: BlockState, entity: RecyclerBlockEntity) {
        val input = entity.getStacks().take(PORTS.inputs.first).first()
        val outputs = entity.getStacks().drop(PORTS.inputs.first)
        if (input.isEmpty.not() && outputs.all { it.isEmpty }) {
            world.recipeManager.values().filter { it.type == RecipeType.CRAFTING }
                .filter { input.item == it.output.item && input.count >= it.output.count }
                .randomOrNull()?.let { recipe ->
                    val n = input.count / recipe.output.count
                    AwesomeLogger.debug("Recycler: ${recipe.output.item.id()}*$n")
                    SimpleInventory(PORTS.outputs.first).apply {
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

    val block = RecyclerBlock(FabricBlockSettings.copyOf(Blocks.FURNACE))

    private lateinit var SCREEN: ScreenHandlerType<RecyclerScreenHandler>
    val screen get() = SCREEN

    operator fun invoke() {
        if (Minecraft.isClient) {
            SCREEN = ScreenHandlerType { syncId, playerInventory ->
                RecyclerScreenHandler(syncId, SimpleSidedInventory(PORTS.size), playerInventory, ArrayPropertyDelegate(
                    PROPERTIES
                ))
            }
            HandledScreens.register(SCREEN) { handler, playerInventory, title ->
                RecyclerScreen(NAME, handler, playerInventory, title)
            }
        }
    }

}
