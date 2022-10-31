package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipeHelper
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.inventory.Inventory
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Smelter : AwesomeMachine<SmelterBlock, SmelterBlock.Entity, SmelterScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    slots = SLOTS.first + SLOTS.second,
    blockProvider = {
        SmelterBlock(FabricBlockSettings.copyOf(Blocks.FURNACE).luminance(0))
    },
    blockEntityProvider = { pos, state ->
        SmelterBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        SmelterScreen(handler, inventory, title)
    },
    screenHandlerProvider = { syncId, playerInventory, inventory, properties ->
        SmelterScreen.Handler(syncId, playerInventory, inventory, properties)
    },
) {

    companion object {

        const val ID = "smelter"
        val SLOTS = 2 to 1

    }

    internal enum class Process(val time: Int) {
        Input(200),
        Output(20),
    }

    init {
        SmelterRecipes()
    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, entity: SmelterBlock.Entity) {
        if (world.isClient()) return
        val helper = AwesomeRecipeHelper(entity as Inventory, SLOTS, SmelterRecipes())
        if (entity.inputProgress > 0) entity.inputProgress--
        val recipe = helper.getRecipe()
        if (recipe != null) {
            when {
                entity.outputProgress < 0 -> {
                    if (entity.inputProgress <= 0) {
                        if (helper.burn(recipe)) {
                            entity.inputProgress = Process.Input.time
                            entity.setPropertyState(Properties.LIT, true)
                        } else {
                            entity.setPropertyState(Properties.LIT, false)
                            return
                        }
                    }
                    entity.outputProgress = Process.Output.time
                }
                entity.outputProgress == 0 -> {
                    helper.craft(recipe)
                    entity.outputProgress = -1
                }
                else -> entity.outputProgress--
            }
        } else {
            entity.outputProgress = -1
            entity.setPropertyState(Properties.LIT, false)
        }
    }

}