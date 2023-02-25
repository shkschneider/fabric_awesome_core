package io.github.shkschneider.awesome.extras.crates

import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.custom.Minecraft
import net.minecraft.screen.ScreenHandlerType

// Inspired from the ShulkerBox
object Crates {

    const val ID = "crate"
    val IO = InputOutput(inputs = 9)

    private lateinit var _block: CrateBlock
    val block: CrateBlock get() = _block

    private lateinit var _screen: ScreenHandlerType<CrateBlockScreen.Handler>
    val screen get() = _screen

    operator fun invoke() {
        _block = CrateBlock()
        _screen = AwesomeRegistries.screen(ID) { syncId, playerInventory ->
            CrateBlockScreen.Handler(null, syncId, playerInventory)
        }
        if (Minecraft.isClient) AwesomeRegistries.screenHandler(screen) { handler, playerInventory, title ->
            CrateBlockScreen(handler, playerInventory, title)
        }
    }

}
