package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.custom.Faces
import io.github.shkschneider.awesome.custom.InputOutput
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
    val IO = InputOutput(inputs = 1 to listOf(Faces.Top))
    private val IGNITE = Minecraft.TICKS
    const val PROPERTIES = 3

    val block = GeneratorBlock(FabricBlockSettings.copyOf(Blocks.FURNACE))

    private lateinit var _screen: ScreenHandlerType<GeneratorScreenHandler>
    val screen get() = _screen

    operator fun invoke() {
        if (Minecraft.isClient) {
            _screen = ScreenHandlerType { syncId, playerInventory ->
                GeneratorScreenHandler(syncId, SimpleSidedInventory(IO.size), playerInventory, ArrayPropertyDelegate(PROPERTIES))
            }
            HandledScreens.register(_screen) { handler, playerInventory, title ->
                GeneratorScreen(ID, handler, playerInventory, title)
            }
        }
    }

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

}
