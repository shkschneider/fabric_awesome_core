package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.MachinePorts
import io.github.shkschneider.awesome.custom.Minecraft
import io.github.shkschneider.awesome.custom.SimpleSidedInventory
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object Generator {

    const val NAME = "generator"
    val ID = AwesomeUtils.identifier(NAME)
    val PORTS = MachinePorts(inputs = 1, outputs = 0)
    val IGNITE = Minecraft.TICKS
    val PROPERTIES = 2

    fun tick(world: World, pos: BlockPos, state: BlockState, entity: GeneratorBlockEntity) {
        entity.power++
    }

    val block = GeneratorBlock(FabricBlockSettings.copyOf(Blocks.FURNACE))

    private lateinit var SCREEN: ScreenHandlerType<GeneratorScreenHandler>
    val screen get() = SCREEN

    operator fun invoke() {
        if (Minecraft.isClient) {
            SCREEN = ScreenHandlerType { syncId, playerInventory ->
                GeneratorScreenHandler(syncId, SimpleSidedInventory(PORTS.size), playerInventory, ArrayPropertyDelegate(PROPERTIES))
            }
            HandledScreens.register(SCREEN) { handler, playerInventory, title ->
                GeneratorScreen(NAME, handler, playerInventory, title)
            }
        }
    }

}
//
//blockEntity.setPropertyState(Properties.LIT, blockEntity.power > 0)
//        // burning
//        if (blockEntity.power > 0) {
//            blockEntity.power--
//        }
//        // igniting
//        if (blockEntity.duration > 0) {
//            blockEntity.progress++
//            // fire
//            if (blockEntity.progress >= blockEntity.duration) {
//                on(AwesomeMachines.FUEL.time)
//            }
//        }
//        // idle
//        if (blockEntity.power <= IGNITE && blockEntity.duration == 0) {
//            if (blockEntity.getStack(INPUT).item == AwesomeMachines.FUEL) {
//                blockEntity.duration = IGNITE
//                blockEntity.progress = 0
//                blockEntity.removeStack(INPUT, 1)
//                if (blockEntity.getStack(INPUT).isEmpty) {
//                    blockEntity.setStack(INPUT, ItemStack.EMPTY)
//                    blockEntity.markDirty()
//                }
//                return
//            }
//        }
//    }
//
//}
