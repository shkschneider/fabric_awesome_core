package io.github.shkschneider.awesome.machines.smelter

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

class SmelterBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(Smelter.ENTITY, pos, state), NamedScreenHandlerFactory, ImplementedInventory {

    override val items: DefaultedList<ItemStack> = DefaultedList.ofSize(Smelter.IO.values().size, ItemStack.EMPTY)

    private var lit = false
    private var inputProgress = -1
    private var outputProgress = -1

    private val properties = object : PropertyDelegate {
        override fun get(index: Int): Int {
            return when (index) {
                Smelter.Properties.InputProgress.ordinal -> inputProgress
                Smelter.Properties.OutputProgress.ordinal -> outputProgress
                else -> -1
            }
        }
        override fun set(index: Int, value: Int) {
            when (index) {
                Smelter.Properties.InputProgress.ordinal -> inputProgress = value
                Smelter.Properties.OutputProgress.ordinal -> outputProgress = value
            }
        }
        override fun size(): Int {
            return Smelter.Properties.values().size
        }
    }

    override fun getDisplayName(): Text {
        return Text.translatable(AwesomeUtils.translatable("block", Smelter.ID))
    }

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return SmelterScreenHandler(syncId, inv, this, properties)
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, items)
        nbt.putBoolean(AwesomeUtils.key(Properties.LIT.name), lit)
        nbt.putInt(AwesomeUtils.key(Smelter.Properties.InputProgress.name), inputProgress)
        nbt.putInt(AwesomeUtils.key(Smelter.Properties.OutputProgress.name), outputProgress)
    }

    override fun readNbt(nbt: NbtCompound) {
        Inventories.readNbt(nbt, items)
        super.readNbt(nbt)
        lit = nbt.getBoolean(AwesomeUtils.key(Properties.LIT.name))
        inputProgress = nbt.getInt(AwesomeUtils.key(Smelter.Properties.InputProgress.name))
        outputProgress = nbt.getInt(AwesomeUtils.key(Smelter.Properties.OutputProgress.name))
    }

    companion object {

        // FIXME does not lit out when empty
        fun tick(world: World, pos: BlockPos, state: BlockState, entity: SmelterBlockEntity) {
            if (world.isClient) return
            if (entity.inputProgress > 0) {
                entity.inputProgress--
            }
            if (entity.outputProgress > 0) entity.outputProgress--
            // abort
            if (entity.getStack(Smelter.IO.InputTop.ordinal).isEmpty || entity.getStack(Smelter.IO.OutputRight.ordinal).count == entity.getStack(Smelter.IO.OutputRight.ordinal).maxCount) {
                entity.outputProgress = -1
                return
            }
            // done
            if (entity.outputProgress == 0) {
                entity.removeStack(Smelter.IO.InputTop.ordinal, Smelter.IO.InputTop.items.count)
                entity.setStack(Smelter.IO.OutputRight.ordinal, ItemStack(Smelter.IO.OutputRight.items.item, entity.getStack(Smelter.IO.OutputRight.ordinal).count + Smelter.IO.OutputRight.items.count))
                entity.outputProgress = -1
            }
            // stand-by
            if (entity.outputProgress < 0) {
                val inputTop = entity.getStack(Smelter.IO.InputTop.ordinal)
                val inputBottom = entity.getStack(Smelter.IO.InputBottom.ordinal)
                val outputRight = entity.getStack(Smelter.IO.OutputRight.ordinal)
                if (
                    inputTop.item == Smelter.IO.InputTop.items.item && inputTop.count >= Smelter.IO.InputTop.items.count
                    && entity.inputProgress > 0 || inputBottom.isEmpty.not()
                    && (outputRight.isEmpty || (outputRight.item == Smelter.IO.OutputRight.items.item && outputRight.count + Smelter.IO.OutputRight.items.count <= outputRight.maxCount))
                ) {
                    // new
                    if (entity.inputProgress <= 0) {
                        entity.removeStack(Smelter.IO.InputBottom.ordinal, Smelter.IO.InputBottom.items.count)
                        entity.inputProgress = Smelter.Properties.InputProgress.time
                    }
                    entity.outputProgress = Smelter.Properties.OutputProgress.time
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