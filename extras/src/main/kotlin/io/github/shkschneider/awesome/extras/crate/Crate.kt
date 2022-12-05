package io.github.shkschneider.awesome.extras.crate

import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.MachinePorts
import io.github.shkschneider.awesome.custom.Minecraft
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.inventory.SimpleInventory
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandlerType

// Inspired from the ShulkerBox
object Crate {

    const val NAME = "crate"
    val ID = AwesomeUtils.identifier(NAME)
    val PORTS = MachinePorts(inputs = 9, outputs = 0)

    val self = CrateBlock()

    private lateinit var SCREEN: ScreenHandlerType<CrateBlockScreenHandler>
    val screen get() = SCREEN

    operator fun invoke() {
        if (Minecraft.isClient) {
            SCREEN = ScreenHandlerType { syncId, playerInventory ->
                CrateBlockScreenHandler(syncId, SimpleInventory(PORTS.size), playerInventory, ArrayPropertyDelegate(0))
            }
            HandledScreens.register(SCREEN) { handler, playerInventory, title ->
                CrateBlockScreen(handler, playerInventory, title)
            }
        }
    }

}
