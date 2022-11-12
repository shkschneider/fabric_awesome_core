package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockScreen
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.text.Text
import kotlin.math.roundToInt

class InfuserScreen(
    name: String,
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineBlockScreen<InfuserScreen.Handler>(name, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        if (handler.progress > 0) {
            val progress = (handler.percent * 24.0).roundToInt()
            drawTexture(matrices, x + 84, y + 23 - 1, 176, 32, progress, 68 - 32)
        }
    }

    class Handler(syncId: Int, inventories: InputOutput.Inventories, properties: PropertyDelegate) : AwesomeMachineBlockScreen.Handler(
        AwesomeMachines.infuser.screen, syncId, inventories, properties
    ) {

        init {
            addProperties(properties)
            addSlots(
                66 to 16,
                66 to 50,
                114 + 4 to 31 + 4,
            )
            addPlayerSlots()
        }

    }

}
