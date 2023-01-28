package io.github.shkschneider.awesome.extras.crate

import io.github.shkschneider.awesome.custom.Faces
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.custom.Minecraft
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.inventory.SimpleInventory
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandlerType

// Inspired from the ShulkerBox
object Crate {

    const val ID = "crate"
    val IO = InputOutput(inputs = 9 to listOf(Faces.Top, Faces.Sides(), Faces.Front, Faces.Back))

    private lateinit var _block: CrateBlock
    val block: CrateBlock get() = _block

    private lateinit var _screen: ScreenHandlerType<CrateBlockScreenHandler>
    val screen get() = _screen

    operator fun invoke() {
        _block = CrateBlock()
        if (Minecraft.isClient) {
            _screen = ScreenHandlerType { syncId, playerInventory ->
                CrateBlockScreenHandler(syncId, SimpleInventory(IO.size), playerInventory, ArrayPropertyDelegate(0))
            }
            HandledScreens.register(_screen) { handler, playerInventory, title ->
                CrateBlockScreen(handler, playerInventory, title)
            }
        }
    }

}
