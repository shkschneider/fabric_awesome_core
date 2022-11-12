package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.items.AwesomeItems
import io.github.shkschneider.awesome.machines.AwesomeMachine
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
        private val FUEL = AwesomeItems.Redstone.flux

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: GeneratorBlock.Entity) {
        if (world.isClient) return
        // do NOT super.tick()
        if (blockEntity.power > 0) {
            blockEntity.setPropertyState(Properties.LIT, true)
            blockEntity.setPropertyState(Properties.POWERED, true)
            blockEntity.power--
        } else if (blockEntity.getStack(0).item == FUEL) {
            blockEntity.removeStack(0, 1)
            blockEntity.power = FUEL.time
            if (blockEntity.getStack(0).isEmpty) {
                blockEntity.setStack(0, ItemStack.EMPTY)
                blockEntity.markDirty()
            }
        }
    }

}
