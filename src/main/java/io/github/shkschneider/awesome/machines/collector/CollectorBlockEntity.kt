package io.github.shkschneider.awesome.machines.collector

import com.google.common.base.Predicates
import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.entities.ImplementedInventory
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.world.World

class CollectorBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(Collector.ENTITY, pos, state), NamedScreenHandlerFactory, ImplementedInventory {

    override val items: DefaultedList<ItemStack> = DefaultedList.ofSize(Collector.IO, ItemStack.EMPTY)

    override fun getDisplayName(): Text {
        return Text.translatable(AwesomeUtils.translatable("block", Collector.ID))
    }

    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return CollectorScreenHandler(syncId, inv, this)
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, items)
    }

    override fun readNbt(nbt: NbtCompound) {
        Inventories.readNbt(nbt, items)
        super.readNbt(nbt)
    }

    companion object {

        fun tick(world: World, pos: BlockPos, state: BlockState, entity: CollectorBlockEntity) {
            if (world.isClient()) return
            val box = Box(pos).expand(8.toDouble())
            world.getEntitiesByClass(ItemEntity::class.java, box, Predicates.alwaysTrue())
                .stream().map { it as ItemEntity }.filter { it.isOnGround }.forEach { itemEntity ->
                    collect(entity, itemEntity)
                }
        }

        // TODO try to take 1 Item per tick, not an entire ItemStack?
        private fun collect(entity: CollectorBlockEntity, itemEntity: ItemEntity) {
            val itemStack = entity.items.firstOrNull { it == ItemStack.EMPTY }
                ?: entity.items.firstOrNull { it.item == itemEntity.stack.item }
                ?: return
            if (itemStack.count < itemStack.maxCount) {
                itemStack.count += itemEntity.stack.count
                itemEntity.setDespawnImmediately()
            }
//            (0 until Collector.IO).map { entity.items[it] }.forEachIndexed { i, itemStack ->
//                if (itemStack.isEmpty) {
//                    entity.items[i] = itemEntity.stack.copy()
//                    itemEntity.stack.count = 0
//                } else if (itemStack.item == itemEntity.stack.item && itemStack.count < itemStack.maxCount) {
//                    entity.items[i].count += itemStack.count
//                    itemEntity.setDespawnImmediately()
//                }
//                if (itemEntity.stack.count == 0) {
//                    itemEntity.setDespawnImmediately()
//                    return@forEachIndexed
//                }
//            }
        }

    }

}