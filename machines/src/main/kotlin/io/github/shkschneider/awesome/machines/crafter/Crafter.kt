package io.github.shkschneider.awesome.machines.crafter

import io.github.shkschneider.awesome.core.ext.test
import io.github.shkschneider.awesome.custom.DummyCraftingInventory
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.custom.SimpleSidedInventory
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import net.minecraft.block.BlockState
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.inventory.CraftingInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.recipe.CraftingRecipe
import net.minecraft.recipe.RecipeType
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Crafter : AwesomeMachine<CrafterBlock.Entity, CrafterScreen.Handler>(
    id = "crafter",
    io = InputOutput(inputs = INVENTORY + GRID * GRID, outputs = 1),
) {

    companion object {

        const val INVENTORY = 5
        const val GRID = 3 // *3

    }

    override fun block(): AwesomeMachineBlock<CrafterBlock.Entity, CrafterScreen.Handler> =
        CrafterBlock(this)

    override fun screen(): ScreenHandlerType<CrafterScreen.Handler> =
        ScreenHandlerType { syncId, playerInventory ->
            CrafterScreen.Handler(syncId, SimpleSidedInventory(io.size), playerInventory, ArrayPropertyDelegate(properties))
        }.also {
            HandledScreens.register(it) { handler, playerInventory, title ->
                CrafterScreen(this, handler, playerInventory, title)
            }
        }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: CrafterBlock.Entity) {
        if (world.isClient) return
        // do NOT super.tick() unless this uses power
        val stacks = DefaultedList.ofSize(GRID * GRID, ItemStack.EMPTY).apply {
            (INVENTORY until io.inputs).forEach { index ->
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
        val outputIndex = io.size - 1
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
