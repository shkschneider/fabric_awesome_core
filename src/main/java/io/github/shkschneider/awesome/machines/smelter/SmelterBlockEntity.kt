package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.entities.ImplementedInventory
import io.github.shkschneider.awesome.machines.recipes.AwesomeRecipeHelper
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.Inventory
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

    override val items: DefaultedList<ItemStack> = DefaultedList.ofSize(Smelter.SLOTS.first + Smelter.SLOTS.second, ItemStack.EMPTY)

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

        fun tick(world: World, pos: BlockPos, state: BlockState, entity: SmelterBlockEntity) {
            if (world.isClient()) return
            val helper = AwesomeRecipeHelper(entity as Inventory, Smelter.SLOTS, SmelterRecipes())
            if (entity.inputProgress > 0) entity.inputProgress--
            val recipe = helper.getRecipe()
            if (recipe != null) {
                when {
                    entity.outputProgress < 0 -> {
                        if (entity.inputProgress <= 0) {
                            if (helper.burn(recipe)) {
                                entity.inputProgress = Smelter.Properties.InputProgress.time
                                world.setBlockState(pos, state.with(Properties.LIT, true))
                                markDirty(world, pos, state)
                            } else {
                                world.setBlockState(pos, state.with(Properties.LIT, false))
                                markDirty(world, pos, state)
                                return
                            }
                        }
                        entity.outputProgress = Smelter.Properties.OutputProgress.time
                    }
                    entity.outputProgress == 0 -> {
                        helper.craft(recipe)
                        entity.outputProgress = -1
                    }
                    else -> entity.outputProgress--
                }
            } else {
                entity.outputProgress = -1
            }
        }

    }

}