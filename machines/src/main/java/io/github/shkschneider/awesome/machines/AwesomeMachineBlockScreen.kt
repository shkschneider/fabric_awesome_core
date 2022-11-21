package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import io.github.shkschneider.awesome.core.AwesomeColors
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.Faces
import io.github.shkschneider.awesome.core.MachinePorts
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.state.property.Properties
import net.minecraft.text.Text

abstract class AwesomeMachineBlockScreen<SH : AwesomeMachineBlockScreen.Handler>(
    name: String,
    handler: SH,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeBlockScreen<SH>(name, handler, playerInventory, title) {

    override fun init() {
        super.init()
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2
    }

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        setShader()
        val power = if (handler.power > 0) getPowerToDraw(handler.power) else 0
        if (power > 0) {
            drawTexture(matrices, x + 8, y + 7 + 55 - power, 176, 111, 192 - 176, power)
            drawTextWithShadow(matrices, textRenderer, Text.of(AwesomeUtils.humanReadable(handler.power)), x + 26, y + 54, AwesomeColors.white)
        }
    }

    open fun getPowerToDraw(power: Int) =
        handler.power * 55 / Properties.POWER.values.max()

    protected fun drawPorts(matrices: MatrixStack, ports: MachinePorts) {
        val input = 177 to 91
        val output = 177 to 101
        val w = 161 - 153
        val h = 12 - 4
        if (ports.inputs.second.any { it is Faces.Top }) {
            drawTexture(matrices, x + 153, y + 4, input.first, input.second, w, h)
        }
        if (ports.inputs.second.any { it is Faces.Side && it.left }) {
            drawTexture(matrices, x + 143, y + 14, input.first, input.second, w, h)
        }
        if (ports.inputs.second.any { it is Faces.Side && it.right }) {
            drawTexture(matrices, x + 163, y + 14, input.first, input.second, w, h)
        }
        if (ports.outputs.second.any { it is Faces.Bottom }) {
            drawTexture(matrices, x + 153, y + 24, output.first, output.second, w, h)
        }
        if (ports.outputs.second.any { it is Faces.Back }) {
            drawTexture(matrices, x + 163, y + 24, output.first, output.second, w, h)
        }
    }

    abstract class Handler(
        screen: ScreenHandlerType<*>,
        syncId: Int,
        sidedInventory: SidedInventory,
        playerInventory: PlayerInventory,
        properties: PropertyDelegate,
    ) : AwesomeBlockScreen.Handler(screen, syncId, sidedInventory, playerInventory, properties) {

        val power: Int get() = properties[0]
        val progress: Int get() = properties[1]
        val percent: Float get() = progress.toFloat() / duration.toFloat()
        val duration: Int get() = properties[2]

    }

}

