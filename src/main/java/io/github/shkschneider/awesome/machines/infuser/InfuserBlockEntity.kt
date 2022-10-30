package io.github.shkschneider.awesome.machines.infuser

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

class InfuserBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(Infuser.ENTITY, pos, state), NamedScreenHandlerFactory, ImplementedInventory {

    override val items: DefaultedList<ItemStack> = DefaultedList.ofSize(Infuser.SLOTS.first + Infuser.SLOTS.second, ItemStack.EMPTY)

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

        fun tick(world: World, pos: BlockPos, state: BlockState, entity: InfuserBlockEntity) {
            if (world.isClient()) return
            val helper = AwesomeRecipeHelper(entity as Inventory, Infuser.SLOTS, InfuserRecipes())
            val recipe = helper.getRecipe()
            if (recipe != null) {
                when {
                    entity.outputProgress < 0 -> {
                        world.setBlockState(pos, state.with(Properties.LIT, true))
                        markDirty(world, pos, state)
                        entity.outputProgress = Infuser.Properties.OutputProgress.time
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