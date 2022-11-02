package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.entities.ImplementedInventory
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant
import net.fabricmc.fabric.api.transfer.v1.storage.Storage
import net.fabricmc.fabric.api.transfer.v1.storage.base.SidedStorageBlockEntity
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

abstract class AwesomeMachineBlockEntity(
    private val id: String,
    type: BlockEntityType<out AwesomeMachineBlockEntity>,
    pos: BlockPos,
    private val state: BlockState,
    slots: InputOutput.Slots,
    private val canInsert: List<Pair<Direction, Boolean>> = emptyList(),
    private val canExtract: List<Pair<Direction, Boolean>> = emptyList(),
    private val screenHandlerProvider: (syncId: Int, inventories: InputOutput.Inventories, properties: PropertyDelegate) -> AwesomeMachineScreenHandler,
) : BlockEntity(type, pos, state), NamedScreenHandlerFactory, ImplementedInventory, SidedStorageBlockEntity {

    override val items: DefaultedList<ItemStack> = DefaultedList.ofSize(slots.size, ItemStack.EMPTY)

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

    override fun getDisplayName(): Text {
        return Text.translatable(AwesomeUtils.translatable("block", id))
    }

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return screenHandlerProvider(syncId, InputOutput.Inventories(this as Inventory, playerInventory), properties)
    }

    @Suppress("UnstableApiUsage")
    override fun getItemStorage(side: Direction): Storage<ItemVariant> {
        val facing = world?.getBlockState(pos)?.get(Properties.FACING) ?: side
        return AwesomeMachineStorage(facing, canInsert, canExtract)
    }

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
