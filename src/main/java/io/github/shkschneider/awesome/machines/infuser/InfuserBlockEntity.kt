package io.github.shkschneider.awesome.machines.infuser

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

class InfuserBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(Infuser.ENTITY, pos, state), NamedScreenHandlerFactory, ImplementedInventory {

    override val items: DefaultedList<ItemStack> = DefaultedList.ofSize(Infuser.IO.values().size, ItemStack.EMPTY)

    private var outputProgress = -1

    private val properties = object : PropertyDelegate {
        override fun get(index: Int): Int {
            return when (index) {
                Infuser.Properties.OutputProgress.ordinal -> outputProgress
                else -> -1
            }
        }
        override fun set(index: Int, value: Int) {
            when (index) {
                Infuser.Properties.OutputProgress.ordinal -> outputProgress = value
            }
        }
        override fun size(): Int {
            return Infuser.Properties.values().size
        }
    }

    override fun getDisplayName(): Text {
        return Text.translatable(AwesomeUtils.translatable("block", Infuser.ID))
    }

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return InfuserScreenHandler(syncId, inv, this, properties)
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, items)
        nbt.putInt(AwesomeUtils.key(Infuser.Properties.OutputProgress.name), outputProgress)
    }

    override fun readNbt(nbt: NbtCompound) {
        Inventories.readNbt(nbt, items)
        super.readNbt(nbt)
        outputProgress = nbt.getInt(AwesomeUtils.key(Infuser.Properties.OutputProgress.name))
    }

    companion object {

        // FIXME does not lit out when empty
        fun tick(world: World, pos: BlockPos, state: BlockState, entity: InfuserBlockEntity) {
            if (world.isClient) return
            if (entity.outputProgress > 0) entity.outputProgress--
            // abort
            if (entity.getStack(Infuser.IO.InputLeft1.ordinal).isEmpty || entity.getStack(Infuser.IO.InputLeft2.ordinal).isEmpty || entity.getStack(Infuser.IO.OutputRight.ordinal).count == entity.getStack(Infuser.IO.OutputRight.ordinal).maxCount) {
                entity.outputProgress = -1
                return
            }
            // done
            if (entity.outputProgress == 0) {
                entity.removeStack(Infuser.IO.InputLeft1.ordinal, Infuser.IO.InputLeft1.items.count)
                entity.removeStack(Infuser.IO.InputLeft2.ordinal, Infuser.IO.InputLeft2.items.count)
                entity.setStack(Infuser.IO.OutputRight.ordinal, ItemStack(Infuser.IO.OutputRight.items.item, entity.getStack(Infuser.IO.OutputRight.ordinal).count + Infuser.IO.OutputRight.items.count))
                entity.outputProgress = -1
            }
            // stand-by
            if (entity.outputProgress < 0) {
                val inputLeft1 = entity.getStack(Infuser.IO.InputLeft1.ordinal)
                val inputLeft2 = entity.getStack(Infuser.IO.InputLeft2.ordinal)
                val outputRight = entity.getStack(Infuser.IO.OutputRight.ordinal)
                if (
                    inputLeft1.item == Infuser.IO.InputLeft1.items.item && inputLeft1.count >= Infuser.IO.InputLeft1.items.count
                    && inputLeft2.item == Infuser.IO.InputLeft2.items.item && inputLeft2.count >= Infuser.IO.InputLeft2.items.count
                    && (outputRight.isEmpty || (outputRight.item == Infuser.IO.OutputRight.items.item && outputRight.count + Infuser.IO.OutputRight.items.count <= outputRight.maxCount))
                ) {
                    // new
                    entity.outputProgress = Infuser.Properties.OutputProgress.time
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