package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.core.Minecraft
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text

class CrusherScreen(
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineScreen<CrusherScreen.Handler>(Crusher.ID, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        if (handler.inputProgress > 0) {
            drawTexture(matrices, x + 8, y + 7, 176, 111, 192 - 176, 166 - 111)
        }
        if (handler.outputProgress > 0) {
            val progress = (Minecraft.TICK - handler.outputProgress) * 24 / Minecraft.TICK
            drawTexture(matrices, x + 80 - 1, y + 36 - 1, 176, 15, progress + 1, 30 - 15)
        }
    }

    @Suppress("USELESS_CAST")
    class Handler(
        syncId: Int,
        inventories: InputOutput.Inventories,
        properties: PropertyDelegate,
    ) : AwesomeMachineScreenHandler(
        AwesomeMachines.crusher.screen as ScreenHandlerType<Handler>, syncId, inventories, properties
    ) {

        init {
            addProperties(properties)
            // 1x1 input
            addSlot(Slot(inventories.internal, 0, 56, 35))
            // 1x1 output
            addSlot(Slot(inventories.internal, 2, 112 + 4, 31 + 4))
            addPlayerSlots()
        }

    }

}
