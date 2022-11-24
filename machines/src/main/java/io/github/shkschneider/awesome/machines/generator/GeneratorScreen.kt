package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.core.AwesomeLogger
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockScreen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
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

    override fun drawForeground(matrices: MatrixStack?, mouseX: Int, mouseY: Int) {
        super.drawForeground(matrices, mouseX, mouseY)
        setShader()
        val power = handler.power
        AwesomeLogger.warn("drawForeground: ${AwesomeUtils.humanReadable(power)}")
    }

    class Handler : AwesomeMachineBlockScreen.Handler {

        constructor(syncId: Int, blockEntity: AwesomeMachineBlockEntity, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(AwesomeMachines.generator.screen, syncId, blockEntity, playerInventory, properties)
        constructor(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(AwesomeMachines.generator.screen, syncId, sidedInventory, playerInventory, properties)

        init {
            addProperties(properties)
            addSlots(
                80 to 53,
            )
            addPlayerSlots()
        }

    }

}
