package io.github.shkschneider.awesome.machines.collector

import com.google.common.base.Predicates
import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.machines.AwesomeMachine
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.ItemEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.world.World

class Collector : AwesomeMachine<CollectorBlock, CollectorBlock.Entity, CollectorScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    slots = SLOTS,
    blockProvider = {
        CollectorBlock(FabricBlockSettings.copyOf(Blocks.FURNACE).luminance(0))
    },
    blockEntityProvider = { pos, state ->
        CollectorBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        CollectorScreen(handler, inventory, title)
    },
    screenHandlerProvider = { syncId, playerInventory, inventory, properties ->
        CollectorScreen.Handler(syncId, playerInventory, inventory, properties)
    },
) {

    companion object {

        const val ID = "collector"
        val SLOTS = Slots(inputs = 0, fuel = null, outputs = 9)

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, entity: CollectorBlock.Entity) {
        if (world.isClient()) return
        val box = Box(pos).expand(8.toDouble())
        world.getEntitiesByClass(ItemEntity::class.java, box, Predicates.alwaysTrue())
            .stream().map { it as ItemEntity }.filter { it.isOnGround }.forEach { itemEntity ->
                collect(entity, itemEntity)
            }
    }

    // TODO try to take 1 Item per tick, not an entire ItemStack?
    private fun collect(entity: CollectorBlock.Entity, itemEntity: ItemEntity) {
        (0 until (entity as Inventory).size()).map { entity.getStack(it) }.forEachIndexed { i, stack ->
            if (itemEntity.stack.count == 0) return@forEachIndexed
            if (stack.isEmpty) {
                entity.setStack(i, itemEntity.stack.copy())
            } else if (stack.item == itemEntity.stack.item && stack.count < stack.maxCount) {
                (stack.count until stack.maxCount).forEach {
                    entity.setStack(i, ItemStack(stack.item, ++stack.count))
                    itemEntity.stack.count--
                }
            }
        }
        if (itemEntity.stack.isEmpty) {
            itemEntity.setDespawnImmediately()
        }
    }

}
