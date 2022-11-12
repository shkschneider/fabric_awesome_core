package io.github.shkschneider.awesome.core

import com.mojang.blaze3d.systems.RenderSystem
import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.custom.InputOutput
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text

abstract class AwesomeBlockScreen<SH : AwesomeBlockScreen.Handler>(
    private val name: String,
    handler: SH,
    playerInventory: PlayerInventory,
    title: Text,
) : HandledScreen<SH>(handler, playerInventory, title) {

    override fun init() {
        super.init()
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2
    }

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, AwesomeUtils.identifier("textures/gui/$name.png"))
        val x = (width - backgroundWidth) / 2
        val y = (height - backgroundHeight) / 2
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight)
        if (handler.power > 0) {
            drawTexture(matrices, x + 8, y + 7, 176, 111, 192 - 176, 166 - 111)
        }
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
        drawMouseoverTooltip(matrices, mouseX, mouseY)
    }

    abstract class Handler(
        screen: ScreenHandlerType<*>,
        syncId: Int,
        private val inventories: InputOutput.Inventories,
        private val properties: PropertyDelegate,
    ) : ScreenHandler(screen, syncId) {

        val power: Int get() = properties[0]
        val progress: Int get() = properties[1]
        val percent: Float get() = progress.toFloat() / duration.toFloat()
        val duration: Int get() = properties[2]

        init {
            // addProperties(properties)
            inventories.internal.onOpen(inventories.player.player)
            // addSlots(...)
            // addPlayerSlots()
        }

        fun addSlots(vararg slots: Pair<Int, Int>) {
            slots.forEachIndexed { index, pair ->
                addSlot(Slot(inventories.internal, index, pair.first, pair.second))
            }
        }

        // https://fabricmc.net/wiki/tutorial:containers
        fun addPlayerSlots() {
            // inventory
            for (i in 0..2) {
                for (l in 0..8) {
                    addSlot(Slot(inventories.player, l + i * 9 + 9, 8 + l * 18, 84 + i * 18))
                }
            }
            // hotbar
            for (i in 0..8) {
                addSlot(Slot(inventories.player, i, 8 + i * 18, 142))
            }
        }

        /**
         * Thanks to Kaupenjoe
         * Link: https://www.youtube.com/c/TKaupenjoe
         * Link: https://fabricmc.net/wiki/tutorial:containers
         * Link: https://github.com/FabricMC/yarn/issues/2944
         *
         */
        override fun transferSlot(player: PlayerEntity, i: Int): ItemStack {
            val slot = slots.getOrNull(i)?.takeIf { it.hasStack() } ?: return ItemStack.EMPTY
            val stack = slot.stack.copy()
            if (i < inventories.internal.size()) {
                if (!insertItem(slot.stack, inventories.internal.size(), slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!insertItem(slot.stack, 0, inventories.internal.size(), false)) {
                return ItemStack.EMPTY
            }
            slot.markDirty()
            return stack
        }

        override fun canUse(player: PlayerEntity): Boolean =
            inventories.internal.canPlayerUse(player)

    }

}

