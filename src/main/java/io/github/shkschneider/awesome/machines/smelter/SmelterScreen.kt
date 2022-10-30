package io.github.shkschneider.awesome.machines.smelter

import com.mojang.blaze3d.systems.RenderSystem
import io.github.shkschneider.awesome.Awesome
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class SmelterScreen(handler: SmelterScreenHandler, playerInventory: PlayerInventory, title: Text) :
    HandledScreen<SmelterScreenHandler>(handler, playerInventory, title) {

    private val TEXTURE = "textures/gui/${Smelter.ID}.png"

    override fun init() {
        super.init()
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2
    }

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShader { GameRenderer.getPositionTexShader() }
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, Identifier(Awesome.ID, TEXTURE))
        val x = (width - backgroundWidth) / 2
        val y = (height - backgroundHeight) / 2
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight)
        if (handler.inputProgress > 0) {
            val progress = handler.inputProgress * 13 / Smelter.Properties.InputProgress.time
            drawTexture(matrices, x + 56, y + 36 + 12 - progress, 176, 12 - progress, 14, progress + 1)
        }
        if (handler.outputProgress > 0) {
            val progress = (Smelter.Properties.OutputProgress.time - handler.outputProgress) * 24 / Smelter.Properties.OutputProgress.time
            drawTexture(matrices, x + 79, y + 34, 176, 14, progress + 1, 16)
        }
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
        drawMouseoverTooltip(matrices, mouseX, mouseY)
    }

}