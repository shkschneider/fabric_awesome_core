package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.entities.ImplementedInventory
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.state.property.Properties
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class CrusherBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(Crusher.ENTITY, pos, state), NamedScreenHandlerFactory, ImplementedInventory {

    override val items: DefaultedList<ItemStack> = DefaultedList.ofSize(Crusher.IO.values().size, ItemStack.EMPTY)

    private var outputProgress = -1

    private val properties = object : PropertyDelegate {
        override fun get(index: Int): Int {
            return when (index) {
                Crusher.Properties.OutputProgress.ordinal -> outputProgress
                else -> -1
            }
        }
        override fun set(index: Int, value: Int) {
            when (index) {
                Crusher.Properties.OutputProgress.ordinal -> outputProgress = value
            }
        }
        override fun size(): Int {
            return Crusher.Properties.values().size
        }
    }

    override fun getDisplayName(): Text {
        return Text.translatable(AwesomeUtils.translatable("block", Crusher.ID))
    }

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return CrusherScreenHandler(syncId, inv, this, properties)
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, items)
        nbt.putInt(AwesomeUtils.key(Crusher.Properties.OutputProgress.name), outputProgress)
    }

    override fun readNbt(nbt: NbtCompound) {
        Inventories.readNbt(nbt, items)
        super.readNbt(nbt)
        outputProgress = nbt.getInt(AwesomeUtils.key(Crusher.Properties.OutputProgress.name))
    }

    companion object {

        fun tick(world: World, pos: BlockPos, state: BlockState, entity: CrusherBlockEntity) {
            if (world.isClient()) return
            if (entity.outputProgress > 0) entity.outputProgress--
            // abort
            if (entity.getStack(Crusher.IO.InputLeft.ordinal).isEmpty || entity.getStack(Crusher.IO.OutputRight.ordinal).count == entity.getStack(Crusher.IO.OutputRight.ordinal).maxCount) {
                entity.outputProgress = -1
                return
            }
            // done
            if (entity.outputProgress == 0) {
                entity.removeStack(Crusher.IO.InputLeft.ordinal, Crusher.IO.InputLeft.items.count)
                entity.setStack(Crusher.IO.OutputRight.ordinal, ItemStack(Crusher.IO.OutputRight.items.item, entity.getStack(Crusher.IO.OutputRight.ordinal).count + Crusher.IO.OutputRight.items.count                 ))
                entity.outputProgress = -1
            }
            // stand-by
            if (entity.outputProgress < 0) {
                val inputLeft = entity.getStack(Crusher.IO.InputLeft.ordinal)
                val outputRight = entity.getStack(Crusher.IO.OutputRight.ordinal)
                if (inputLeft.item == Crusher.IO.InputLeft.items.item
                    // new
                    && (outputRight.isEmpty || (outputRight.item == Crusher.IO.OutputRight.items.item && outputRight.count + Crusher.IO.OutputRight.items.count <= outputRight.maxCount))
                ) {
                    entity.outputProgress = Crusher.Properties.OutputProgress.time
                    world.setBlockState(pos, state.with(Properties.LIT, true))
                    markDirty(world, pos, state)
                } else {
                    // nothing
                    world.setBlockState(pos, state.with(Properties.LIT, false))
                    markDirty(world, pos, state)
                }
            }
        }

    }

}