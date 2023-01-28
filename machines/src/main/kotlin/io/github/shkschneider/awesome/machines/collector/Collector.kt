package io.github.shkschneider.awesome.machines.collector

import com.google.common.base.Predicates
import io.github.shkschneider.awesome.core.ext.getEnchantmentLevel
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.custom.SimpleSidedInventory
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import net.minecraft.block.BlockState
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.ItemEntity
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.world.World

class Collector : AwesomeMachine<CollectorBlock.Entity, CollectorScreen.Handler>(
    id = "collector",
    io = InputOutput(inputs = 1, outputs = 9),
    properties = PROPERTIES + 1,
) {

    override fun block(): AwesomeMachineBlock<CollectorBlock.Entity, CollectorScreen.Handler> =
        CollectorBlock(this)

    override fun screen(): ScreenHandlerType<CollectorScreen.Handler> =
        ScreenHandlerType { syncId, playerInventory ->
            CollectorScreen.Handler(syncId, SimpleSidedInventory(io.size), playerInventory, ArrayPropertyDelegate(properties))
        }.also {
            HandledScreens.register(it) { handler, playerInventory, title ->
                CollectorScreen(this, handler, playerInventory, title)
            }
        }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: CollectorBlock.Entity) {
        if (world.isClient) return
        blockEntity.efficiency = 1 + io.getInputs(blockEntity).maxOf { it.second.getEnchantmentLevel(Enchantments.EFFICIENCY) }
        val box = Box(pos).expand(blockEntity.efficiency.toDouble())
        world.getEntitiesByClass(ItemEntity::class.java, box, Predicates.alwaysTrue())
            .stream().map { it as ItemEntity }.filter { it.isOnGround }.forEach { itemEntity ->
                if (blockEntity.insert(itemEntity.stack).isEmpty) {
                    itemEntity.discard()
                }
                blockEntity.markDirty()
            }
    }

}
