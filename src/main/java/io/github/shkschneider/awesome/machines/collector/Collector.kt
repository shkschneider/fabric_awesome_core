package io.github.shkschneider.awesome.machines.collector

import com.google.common.base.Predicates
import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.core.AwesomeBlockWithScreen
import io.github.shkschneider.awesome.custom.InputOutput
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.ItemEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.slot.Slot
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.world.World

class Collector : AwesomeBlockWithScreen(
    id = AwesomeUtils.identifier("collector"),
    settings = FabricBlockSettings.copyOf(Blocks.FURNACE).luminance(0),
    slots = InputOutput.Slots(inputs = 0, fuel = null, outputs = 9),
) {

    override val properties: Map<Int, Int> = emptyMap()

    override fun initSlots(inventory: Inventory): Pair<List<Slot>, Boolean> = listOf(
        Slot(inventory, 0, 62, 17),
        Slot(inventory, 1, 80, 17),
        Slot(inventory, 2, 98, 17),
        Slot(inventory, 3, 62, 35),
        Slot(inventory, 4, 80, 35),
        Slot(inventory, 5, 98, 35),
        Slot(inventory, 6, 62, 53),
        Slot(inventory, 7, 80, 53),
        Slot(inventory, 8, 98, 53),
    ) to true

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: MachineBlockEntity) {
        if (world.isClient()) return
        val box = Box(pos).expand(8.toDouble())
        world.getEntitiesByClass(ItemEntity::class.java, box, Predicates.alwaysTrue())
            .stream().map { it as ItemEntity }.filter { it.isOnGround }.forEach { itemEntity ->
                collect(inventory, itemEntity)
            }
    }

    // TODO try to take 1 Item per tick, not an entire ItemStack?
    private fun collect(inventory: Inventory, itemEntity: ItemEntity) {
        (0 until inventory.size()).map { inventory.getStack(it) }.forEachIndexed { i, stack ->
            if (itemEntity.stack.count == 0) return@forEachIndexed
            if (stack.isEmpty) {
                inventory.setStack(i, itemEntity.stack.copy())
            } else if (stack.item == itemEntity.stack.item && stack.count < stack.maxCount) {
                (stack.count until stack.maxCount).forEach {
                    inventory.setStack(i, ItemStack(stack.item, ++stack.count))
                    itemEntity.stack.count--
                }
            }
        }
        if (itemEntity.stack.isEmpty) {
            itemEntity.setDespawnImmediately()
        }
    }

}
