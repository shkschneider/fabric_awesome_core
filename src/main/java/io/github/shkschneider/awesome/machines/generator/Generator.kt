package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.core.Minecraft
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Generator : AwesomeMachine<GeneratorBlock, GeneratorBlock.Entity, GeneratorScreen.Handler>(
    id = AwesomeUtils.identifier(ID),
    slots = SLOTS,
    blockProvider = {
        GeneratorBlock(FabricBlockSettings.copyOf(Blocks.FURNACE))
    },
    blockEntityProvider = { pos, state ->
        GeneratorBlock.Entity(pos, state)
    },
    screenProvider = { handler, inventory, title ->
        GeneratorScreen(ID, handler, inventory, title)
    },
    screenHandlerProvider = { syncId, inventories, properties ->
        GeneratorScreen.Handler(syncId, inventories, properties)
    },
) {

    companion object {

        const val ID = "generator"
        val SLOTS = InputOutput.Slots(inputs = 1, outputs = 0)
        const val INPUT = 0
        val IGNITE = Minecraft.TICKS

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: GeneratorBlock.Entity) {
        if (world.isClient) return
        // do NOT super.tick()
        fun on(time: Int) {
            super.on(blockEntity, time)
            blockEntity.power = time
            blockEntity.duration = 0
            blockEntity.progress = 0
            blockEntity.setPropertyState(Properties.POWERED, true)
        }
        fun off() {
            super.off(blockEntity)
            blockEntity.duration = 0
            blockEntity.progress = 0
            blockEntity.setPropertyState(Properties.POWERED, false)
        }
        blockEntity.setPropertyState(Properties.LIT, blockEntity.power > 0)
        // burning
        if (blockEntity.power > 0) {
            blockEntity.power--
        }
        // igniting
        if (blockEntity.duration > 0) {
            blockEntity.progress++
            // fire
            if (blockEntity.progress >= blockEntity.duration) {
                on(AwesomeMachines.FUEL.time)
            }
        }
        // idle
        if (blockEntity.power <= IGNITE && blockEntity.duration == 0) {
            if (blockEntity.getStack(INPUT).item == AwesomeMachines.FUEL) {
                blockEntity.duration = IGNITE
                blockEntity.progress = 0
                blockEntity.removeStack(INPUT, 1)
                if (blockEntity.getStack(INPUT).isEmpty) {
                    blockEntity.setStack(INPUT, ItemStack.EMPTY)
                    blockEntity.markDirty()
                }
                return
            }
        }
    }

}
