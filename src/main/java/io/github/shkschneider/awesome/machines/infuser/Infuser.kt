package io.github.shkschneider.awesome.machines.infuser

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

class Infuser : AwesomeMachine<InfuserBlock, InfuserBlock.Entity, InfuserScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    slots = SLOTS.first + SLOTS.second,
    blockProvider = {
        InfuserBlock(FabricBlockSettings.copyOf(Blocks.FURNACE).luminance(0))
    },
    blockEntityProvider = { pos, state ->
        InfuserBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        InfuserScreen(handler, inventory, title)
    },
    screenHandlerProvider = { syncId, playerInventory, inventory, properties ->
        InfuserScreen.Handler(syncId, playerInventory, inventory, properties)
    },
) {

    companion object {

        const val ID = "infuser"
        val SLOTS = 2 to 1

    }

    internal enum class Process(val time: Int) {
        Input(0),
        Output(20),
    }

    init {
        InfuserRecipes()
    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, entity: InfuserBlock.Entity) {
        if (world.isClient()) return
        val helper = AwesomeRecipeHelper(entity as Inventory, SLOTS, InfuserRecipes())
        val recipe = helper.getRecipe()
        if (recipe != null) {
            when {
                entity.outputProgress < 0 -> {
                    entity.setPropertyState(Properties.LIT, true)
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