package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockScreen
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.text.Text
import kotlin.math.roundToInt

class SmelterScreen(
    name: String,
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineBlockScreen<SmelterScreen.Handler>(name, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        setShader()
        if (handler.progress > 0) {
            val progress = (handler.percent * 24.0).roundToInt()
            drawTexture(matrices, x + 80 - 1, y + 36 - 1, 176, 15, progress, 30 - 15)
        }
    }

    class Handler(syncId: Int, inventories: InputOutput.Inventories, properties: PropertyDelegate) : AwesomeMachineBlockScreen.Handler(
        AwesomeMachines.smelter.screen, syncId, inventories, properties
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
