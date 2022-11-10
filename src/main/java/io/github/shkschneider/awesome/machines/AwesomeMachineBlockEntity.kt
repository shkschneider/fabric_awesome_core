package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import io.github.shkschneider.awesome.custom.Faces
import io.github.shkschneider.awesome.custom.Faces.Companion.relativeFace
import io.github.shkschneider.awesome.custom.ImplementedInventory
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.recipes.AwesomeRecipe
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.state.property.BooleanProperty
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

abstract class AwesomeMachineBlockEntity(
    private val id: String,
    type: BlockEntityType<out AwesomeMachineBlockEntity>,
    pos: BlockPos,
    private val state: BlockState,
    private val slots: InputOutput.Slots,
    private val recipes: List<AwesomeRecipe<out AwesomeMachineBlockEntity>>,
    private val screenHandlerProvider: (syncId: Int, inventories: InputOutput.Inventories, properties: PropertyDelegate) -> AwesomeBlockScreen.Handler,
) : BlockEntity(type, pos, state), NamedScreenHandlerFactory, ImplementedInventory, SidedInventory {

    //region properties

    var inputProgress = -1
    var outputProgress = -1

    protected val properties = object : PropertyDelegate {
        override fun get(index: Int): Int {
            return when (index) {
                0 -> inputProgress
                1 -> outputProgress
                else -> -1
            }
        }
        override fun set(index: Int, value: Int) {
            when (index) {
                0 -> inputProgress = value
                1 -> outputProgress = value
            }
        }
        override fun size(): Int {
            return 2
        }
    }

    fun getPropertyState(property: BooleanProperty): Boolean {
        return state.get(property)
    }

    fun setPropertyState(property: BooleanProperty, value: Boolean) {
        world?.setBlockState(pos, state.with(property, value))
        markDirty(world, pos, state)
    }

    //endregion

    //region Inventory

    override val items: DefaultedList<ItemStack> = DefaultedList.ofSize(slots.size, ItemStack.EMPTY)

    override fun getAvailableSlots(side: Direction?): IntArray {
        return (0 until slots.size).toList().toIntArray()
    }

    override fun canInsert(slot: Int, stack: ItemStack, dir: Direction?): Boolean {
        dir ?: return false
        val face = dir.relativeFace(state)
        return slots.isOutput(slot).not()
                && (face == Faces.Top || face is Faces.Side)
                && recipes.any { recipe -> recipe.inputs.any { it.item == stack.item } }
    }

    override fun canExtract(slot: Int, stack: ItemStack, dir: Direction): Boolean {
        val face = dir.relativeFace(state)
        return slots.isOutput(slot)
                && face == Faces.Bottom
                && recipes.any { recipe -> recipe.output.item == stack.item }
    }

    //endregion

    //region ScreenHandler

    override fun getDisplayName(): Text {
        return Text.translatable(AwesomeUtils.translatable("block", id))
    }

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): AwesomeBlockScreen.Handler {
        return screenHandlerProvider(syncId, InputOutput.Inventories(this as Inventory, playerInventory), properties)
    }

    //endregion

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, items)
        nbt.putInt(AwesomeUtils.key("input", "progress"), inputProgress)
        nbt.putInt(AwesomeUtils.key("output", "progress"), outputProgress)
    }

    override fun readNbt(nbt: NbtCompound) {
        Inventories.readNbt(nbt, items)
        super.readNbt(nbt)
        inputProgress = nbt.getInt(AwesomeUtils.key("input", "progress"))
        outputProgress = nbt.getInt(AwesomeUtils.key("output", "progress"))
    }

}
