package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import io.github.shkschneider.awesome.core.Minecraft
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text

class InfuserScreen(
    name: String,
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeBlockScreen<InfuserScreen.Handler>(name, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        if (handler.inputProgress > 0) {
            drawTexture(matrices, x + 8, y + 7, 176, 111, 192 - 176, 166 - 111)
        }
        if (handler.outputProgress > 0) {
            val progress = (Minecraft.TICK - handler.outputProgress) * 24 / Minecraft.TICK
            drawTexture(matrices, x + 84, y + 23 - 1, 176, 32, progress + 1, 68 - 32)
        }
    }

    @Suppress("USELESS_CAST")
    class Handler(
        syncId: Int,
        inventories: InputOutput.Inventories,
        properties: PropertyDelegate,
    ) : AwesomeBlockScreen.Handler(
        AwesomeMachines.infuser.screen as ScreenHandlerType<Handler>, syncId, inventories, properties
    ) {

        init {
            addProperties(properties)
            // 1x1 input
            addSlot(Slot(inventories.internal, 0, 66, 16))
            // 1x1 input
            addSlot(Slot(inventories.internal, 1, 66, 50))
            // 1x1 output
            addSlot(Slot(inventories.internal, 2, 114 + 4, 31 + 4))
            addPlayerSlots()
        }

    }

}
