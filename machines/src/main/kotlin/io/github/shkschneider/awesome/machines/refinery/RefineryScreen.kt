package io.github.shkschneider.awesome.machines.refinery

import io.github.shkschneider.awesome.machines.AwesomeMachineBlockScreen
import io.github.shkschneider.awesome.AwesomeMachines
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.text.Text
import kotlin.math.roundToInt

class RefineryScreen(
    name: String,
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineBlockScreen<RefineryScreen.Handler>(name, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        setShader()
        if (handler.progress > 0) {
            val progress = (handler.percent * 24.0).roundToInt()
            drawTexture(matrices, x + 80 - 1, y + 36 - 1, 176, 15, progress, 30 - 15)
        }
        drawPorts(matrices, Refinery.PORTS)
    }

    class Handler(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate) : AwesomeMachineBlockScreen.Handler(
        AwesomeMachines.refinery.screen, syncId, sidedInventory, playerInventory, properties
    ) {

        init {
            addProperties(properties)
            addSlots(
                56 to 35,
                112 + 4 to 31 + 4,
            )
            addPlayerSlots()
        }

    }

}
