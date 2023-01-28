package io.github.shkschneider.awesome.machines.collector

import com.google.common.base.Predicates
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.ext.insert
import io.github.shkschneider.awesome.custom.Faces
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
    io = IO,
    blockProvider = {
        CollectorBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK))
    },
    blockEntityProvider = { pos, state ->
        CollectorBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        CollectorScreen(ID, handler, inventory, title)
    },
    screenHandlerProvider = { syncId, sidedInventory, playerInventory, properties ->
        CollectorScreen.Handler(syncId, sidedInventory, playerInventory, properties)
    },
) {

    companion object {

        const val ID = "collector"
        val IO = InputOutput(outputs = 9 to listOf(Faces.Front, Faces.Back, Faces.Sides(), Faces.Bottom))

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: CollectorBlock.Entity) {
        if (world.isClient) return
        // do NOT super.tick() unless this uses power
        val box = Box(pos).expand(8.toDouble())
        world.getEntitiesByClass(ItemEntity::class.java, box, Predicates.alwaysTrue())
            .stream().map { it as ItemEntity }.filter { it.isOnGround }.forEach { itemEntity ->
                itemEntity.stack.count = blockEntity.insert(itemEntity.stack).count
                if (itemEntity.stack.isEmpty) itemEntity.discard()
                blockEntity.markDirty()
            }
    }

}
