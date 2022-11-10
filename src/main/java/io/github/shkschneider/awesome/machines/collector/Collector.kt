package io.github.shkschneider.awesome.machines.collector

import com.google.common.base.Predicates
import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.core.ext.insert
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachine
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.ItemEntity
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
        CollectorScreen(ID, handler, inventory, title)
    },
    screenHandlerProvider = { syncId, inventories, properties ->
        CollectorScreen.Handler(syncId, inventories, properties)
    },
) {

    companion object {

        const val ID = "collector"
        val SLOTS = InputOutput.Slots(inputs = 0, outputs = 9)

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, entity: CollectorBlock.Entity) {
        if (world.isClient()) return
        val box = Box(pos).expand(8.toDouble())
        world.getEntitiesByClass(ItemEntity::class.java, box, Predicates.alwaysTrue())
            .stream().map { it as ItemEntity }.filter { it.isOnGround }.forEach { itemEntity ->
                entity.insert(itemEntity.stack)
            }
    }

}
