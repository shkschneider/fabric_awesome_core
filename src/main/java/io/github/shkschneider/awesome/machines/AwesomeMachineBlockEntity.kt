package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.core.AwesomeBlockEntity
import io.github.shkschneider.awesome.custom.MachinePorts
import io.github.shkschneider.awesome.recipes.AwesomeRecipe
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

abstract class AwesomeMachineBlockEntity(
    private val id: String,
    type: BlockEntityType<out AwesomeMachineBlockEntity>,
    pos: BlockPos,
    state: BlockState,
    private val ports: MachinePorts,
    private val recipes: List<AwesomeRecipe<out AwesomeMachineBlockEntity>>,
    private val screenHandlerProvider: (syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate) -> AwesomeMachineBlockScreen.Handler,
) : AwesomeBlockEntity.WithInventory(id, type, pos, state, ports, PROPERTIES to 0), NamedScreenHandlerFactory {

    //region Properties

    companion object {

        const val PROPERTIES = 3

    }

    var power: Int
        get() = properties.get(0)
        set(value) = properties.set(0, value)
    var progress: Int
        get() = properties.get(1)
        set(value) = properties.set(1, value)
    var duration: Int
        get() = properties.get(2)
        set(value) = properties.set(2, value)

    //endregion

    //region Inventory

    override fun canInsert(slot: Int, stack: ItemStack, dir: Direction?): Boolean =
        super.canInsert(slot, stack, dir)
                && recipes.any { recipe -> recipe.inputs.any { it.item == stack.item } }

    override fun canExtract(slot: Int, stack: ItemStack, dir: Direction): Boolean =
        super.canExtract(slot, stack, dir)
                && recipes.any { recipe -> recipe.output.item == stack.item }

    //endregion

    //region ScreenHandler

    override fun getDisplayName(): Text =
        Text.translatable(AwesomeUtils.translatable("block", id))

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): AwesomeMachineBlockScreen.Handler =
        screenHandlerProvider(syncId, this as SidedInventory, playerInventory, properties)

    //endregion

}
