package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import io.github.shkschneider.awesome.core.ext.readNbt
import io.github.shkschneider.awesome.core.ext.writeNbt
import io.github.shkschneider.awesome.custom.Faces
import io.github.shkschneider.awesome.custom.Faces.Companion.relativeFace
import io.github.shkschneider.awesome.custom.IInventory
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
import net.minecraft.state.property.Property
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

abstract class AwesomeMachineBlockEntity(
    private val id: String,
    type: BlockEntityType<out AwesomeMachineBlockEntity>,
    pos: BlockPos,
    private val state: BlockState,
    val slots: InputOutput.Slots,
    private val recipes: List<AwesomeRecipe<out AwesomeMachineBlockEntity>>,
    private val screenHandlerProvider: (syncId: Int, inventories: InputOutput.Inventories, properties: PropertyDelegate) -> AwesomeBlockScreen.Handler,
) : BlockEntity(type, pos, state), NamedScreenHandlerFactory, IInventory, SidedInventory {

    //region properties

    companion object {

        const val PROPERTIES = 3

    }

    var power = 0
    var progress = -1
    var duration = 0

    protected val properties = object : PropertyDelegate {
        override fun get(index: Int): Int {
            return when (index) {
                0 -> power
                1 -> progress
                2 -> duration
                else -> -1
            }
        }
        override fun set(index: Int, value: Int) {
            when (index) {
                0 -> power = value
                1 -> progress = value
                2 -> duration = value
                else -> throw IllegalArgumentException()
            }
        }
        override fun size(): Int {
            return PROPERTIES
        }
    }

    fun <T : Comparable<T>> getPropertyState(property: Property<T>): T {
        if (state.properties.none { it == property }) throw IllegalArgumentException()
        return world?.getBlockState(pos)?.get(property) ?: state.get(property)
    }

    fun <T : Comparable<T>, V : T> setPropertyState(property: Property<T>, value: V) {
        if (state.properties.none { it == property }) throw IllegalArgumentException()
        with(state.with(property, value)) {
            world?.setBlockState(pos, this)
            markDirty(world, pos, this)
        }
    }

    //endregion

    //region Inventory

    override val items: DefaultedList<ItemStack> = DefaultedList.ofSize(slots.size, ItemStack.EMPTY)

    override fun getAvailableSlots(side: Direction?): IntArray =
        (0 until slots.size).toList().toIntArray()

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

    override fun getDisplayName(): Text =
        Text.translatable(AwesomeUtils.translatable("block", id))

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): AwesomeBlockScreen.Handler =
        screenHandlerProvider(syncId, InputOutput.Inventories(this as Inventory, playerInventory), properties)

    //endregion

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, items)
        properties.writeNbt(nbt)
    }

    override fun readNbt(nbt: NbtCompound) {
        properties.readNbt(nbt)
        Inventories.readNbt(nbt, items)
        super.readNbt(nbt)
    }

}
