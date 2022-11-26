package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import io.github.shkschneider.awesome.core.AwesomeColors
import io.github.shkschneider.awesome.core.AwesomeLogger
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text

class GeneratorScreen(
    name: String,
    handler: GeneratorScreenHandler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeBlockScreen<GeneratorScreenHandler>(name, handler, playerInventory, title) {

    override fun init() {
        super.init()
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2
    }

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        setShader()
        if (handler.duration > 0) {
            val progress = handler.progress * 13 / handler.duration
            if (progress > 0) drawTexture(matrices, x + 80, y + 36 + 13 - progress - 1, 176, 13 - progress - 1, 190 - 176, progress + 1)
        }
    }

    override fun drawForeground(matrices: MatrixStack?, mouseX: Int, mouseY: Int) {
        super.drawForeground(matrices, mouseX, mouseY)
        setShader()
        AwesomeLogger.warn("drawForeground: ${handler.power}")
        if (handler.power > 0) {
            drawTexture(matrices, x + 8, y + 7 + 55 - handler.power.toInt(), 176, 111, 192 - 176, handler.power.toInt())
            drawTextWithShadow(matrices, textRenderer, Text.of(AwesomeUtils.humanReadable(handler.power)), x + 26, y + 54, AwesomeColors.white)
        }
    }

}
