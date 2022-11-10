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
        GeneratorScreen(handler, inventory, title)
    },
    screenHandlerProvider = { syncId, inventories, properties ->
        GeneratorScreen.Handler(syncId, inventories, properties)
    },
) {

    companion object {

        const val ID = "generator"
        val SLOTS = InputOutput.Slots(inputs = 1, outputs = 0)

    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, entity: GeneratorBlock.Entity) {
        if (world.isClient()) return
        if (entity.inputProgress > 0) {
            entity.inputProgress--
        } else if (entity.getStack(0).isEmpty.not()) {
            entity.removeStack(0, 1)
            entity.inputProgress = AwesomeItems.Redstone.flux.time
            if (entity.getStack(0).isEmpty) {
                entity.setStack(0, ItemStack.EMPTY)
                entity.markDirty()
            }
        }
        // FIXME
        world.setBlockState(pos, state.with(Properties.LIT, entity.inputProgress > 0))
    }

}
