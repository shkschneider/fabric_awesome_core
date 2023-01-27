package io.github.shkschneider.awesome.machines.quarry

import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import io.github.shkschneider.awesome.core.AwesomeColors
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text

class QuarryScreen(
    name: String,
    handler: QuarryScreenHandler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeBlockScreen<QuarryScreenHandler>(name, handler, playerInventory, title) {

    override fun init() {
        super.init()
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2
    }

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        setShader()
        if (handler.progress > 0) {
            val progress = handler.progress * 55 / handler.duration
            drawTexture(matrices, x + 8, y + 7 + 55 - progress - 1, 176, 111, 192 - 176, progress + 1)
        }
        drawTextWithShadow(matrices, textRenderer, Text.of("${handler.efficiency * handler.fortune}/s"), x + 26, y + 54, AwesomeColors.white)
    }

}
