package io.github.shkschneider.awesome.machines.crafter

import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.MachinePorts
import io.github.shkschneider.awesome.core.ext.test
import io.github.shkschneider.awesome.custom.DummyCraftingInventory
import io.github.shkschneider.awesome.machines.AwesomeMachine
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.inventory.CraftingInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.recipe.CraftingRecipe
import net.minecraft.recipe.RecipeType
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Crafter : AwesomeMachine<CrafterBlock, CrafterBlock.Entity, CrafterScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    ports = PORTS,
    blockProvider = {
        CrafterBlock(FabricBlockSettings.copyOf(Blocks.FURNACE))
    },
    blockEntityProvider = { pos, state ->
        CrafterBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        CrafterScreen(ID, handler, inventory, title)
    },
    screenHandlerProvider = { syncId, sidedInventory, playerInventory, properties ->
        CrafterScreen.Handler(syncId, sidedInventory, playerInventory, properties)
    },
) {

    companion object {

        const val ID = "crafter"
        const val INVENTORY = 5
        const val GRID = 3
        val PORTS = MachinePorts(inputs = INVENTORY + GRID * GRID, outputs = 1)

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: CrafterBlock.Entity) {
        if (world.isClient) return
        // do NOT super.tick() unless this uses power
        val stacks = DefaultedList.ofSize(GRID * GRID, ItemStack.EMPTY).apply {
            (INVENTORY until PORTS.inputs.first).forEach { index ->
                set(index - INVENTORY, (blockEntity as Inventory).getStack(index))
            }
        }
        val craftingGrid = DummyCraftingInventory(GRID, GRID, stacks)
        world.recipeManager.getFirstMatch(RecipeType.CRAFTING, craftingGrid, world).orElse(null)
            ?.takeUnless { recipe -> recipe.test(blockEntity.items.take(INVENTORY)) < 0 }
            ?.let { recipe -> craft(blockEntity, craftingGrid, recipe) }
    }

    private fun craft(entity: CrafterBlock.Entity, inventory: CraftingInventory, recipe: CraftingRecipe): Int {
        val stack = recipe.craft(inventory)
        val outputIndex = PORTS.size - 1
        if (stack.isEmpty) return -1
        if (entity.getStack(outputIndex).count + stack.count > entity.getStack(outputIndex).maxCount) return -2
        recipe.ingredients.forEach { ingredient ->
            val index = entity.items.indexOfFirst { ingredient.test(it) }
            entity.removeStack(index, 1)
        }
        if (entity.getStack(outputIndex).isEmpty) {
            entity.setStack(outputIndex, stack)
        } else if (entity.getStack(outputIndex).item == stack.item) {
            entity.getStack(outputIndex).count += stack.count
        } else {
            return 0
        }
        return stack.count
    }

}
