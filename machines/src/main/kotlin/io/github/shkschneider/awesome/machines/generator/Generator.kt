package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.custom.MachinePorts
import io.github.shkschneider.awesome.custom.Minecraft
import io.github.shkschneider.awesome.custom.SimpleSidedInventory
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.item.ItemStack
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object Generator {

    const val ID = "generator"
    val PORTS = MachinePorts(inputs = 1, outputs = 0)
    private val IGNITE = Minecraft.TICKS
    const val PROPERTIES = 3

    fun tick(world: World, pos: BlockPos, state: BlockState, entity: GeneratorBlockEntity) = with(entity) {
        if (world.isClient) return
        // burning
        if (power > 0) {
            power--
        }
        // igniting
        if (duration > 0) {
            progress++
            // fire
            if (progress >= duration) {
                power = AwesomeMachines.fuel.time
                setPropertyState(state.with(Properties.LIT, true))
                progress = 0
                duration = 0
            }
        }
        // idle
        if (power <= IGNITE && duration == 0) {
            if (getStack(0).item == AwesomeMachines.fuel) {
                duration = IGNITE
                progress = 0
                removeStack(0, 1)
                if (getStack(0).isEmpty) {
                    setStack(0, ItemStack.EMPTY)
                }
            }
        }
        setPropertyState(state.with(Properties.LIT, power > 0))
        markDirty()
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
                GeneratorScreen(ID, handler, playerInventory, title)
            }
        }
    }

}
