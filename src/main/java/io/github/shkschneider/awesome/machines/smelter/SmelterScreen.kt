package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import io.github.shkschneider.awesome.machines.AwesomeMachineTicker
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text

class SmelterScreen(
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineScreen<SmelterScreen.Handler>(Smelter.ID, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        if (handler.inputProgress > 0) {
            val progress = handler.inputProgress * 13 / AwesomeMachineTicker.INPUT
            drawTexture(matrices, x + 57 - 1, y + 37 + (49 - 37) - progress - 1, 176, 13 - progress - 1, 189 - 176, progress + 1)
        }
        if (handler.outputProgress > 0) {
            val progress = (AwesomeMachineTicker.OUTPUT - handler.outputProgress) * 24 / AwesomeMachineTicker.OUTPUT
            drawTexture(matrices, x + 80 - 1, y + 36 - 1, 176, 15, progress + 1, 30 - 15)
        }
    }

    @Suppress("USELESS_CAST")
    class Handler(
        syncId: Int,
        inventories: InputOutput.Inventories,
        properties: PropertyDelegate,
    ) : AwesomeMachineScreenHandler(
        AwesomeMachines.smelter.screen as ScreenHandlerType<Handler>, syncId, inventories, properties
    ) {

        init {
            addProperties(properties)
            addSlot(Slot(inventories.internal, 0, 56, 17)) // input
            addSlot(Slot(inventories.internal, 1, 56, 53)) // fuel
            addSlot(Slot(inventories.internal, 2, 112 + 4, 31 + 4)) // output
            addPlayerSlots()
        }

    }

}
