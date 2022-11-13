package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockScreen
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.text.Text

class GeneratorScreen(
    name: String,
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineBlockScreen<GeneratorScreen.Handler>(name, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        setShader()
        if (handler.progress > 0) {
            val progress = handler.progress * 13 / handler.duration
            if (progress > 0) drawTexture(matrices, x + 80, y + 36 + 13 - progress - 1, 176, 13 - progress - 1, 190 - 176, progress + 1)
        }
    }

    override fun getPowerToDraw(power: Int): Int =
        handler.power * 55 / AwesomeMachines.FUEL.time

    class Handler(
        syncId: Int,
        inventories: InputOutput.Inventories,
        properties: PropertyDelegate,
    ) : AwesomeMachineBlockScreen.Handler(
        AwesomeMachines.generator.screen, syncId, inventories, properties
    ) {

        init {
            addProperties(properties)
            addSlots(
                80 to 53,
            )
            addPlayerSlots()
        }

    }

}
