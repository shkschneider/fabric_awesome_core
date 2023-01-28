package io.github.shkschneider.awesome.core

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
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

    protected fun setShader() {
        // TODO there is something I don't quite get here
        // when drawing 1+ textures, only the first appears
        // resetting the Shader, even in the children (calling super already) fixes it
        // FIXME i doubt this is how it should be done -- nor optimized :/
        RenderSystem.setShader(GameRenderer::getRenderTypeTextProgram)
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, AwesomeUtils.identifier("textures/gui/$name.png"))
    }

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        setShader()
        val x = (width - backgroundWidth) / 2
        val y = (height - backgroundHeight) / 2
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight)
    }

    override fun drawForeground(matrices: MatrixStack, mouseX: Int, mouseY: Int) {
        super.drawForeground(matrices, mouseX, mouseY)
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
        drawMouseoverTooltip(matrices, mouseX, mouseY)
    }

    abstract class Handler(
        screen: ScreenHandlerType<*>,
        syncId: Int,
        protected val sidedInventory: Inventory,
        protected val playerInventory: PlayerInventory,
        protected val properties: PropertyDelegate,
    ) : ScreenHandler(screen, syncId) {

        init {
            // addProperties(properties)
            sidedInventory.onOpen(playerInventory.player)
            // addSlots(...)
            // addPlayerSlots()
        }

        fun addSlots(vararg slots: Pair<Int, Int>) {
            slots.forEachIndexed { index, pair ->
                addSlot(Slot(sidedInventory, index, pair.first, pair.second))
            }
        }

        // https://fabricmc.net/wiki/tutorial:containers
        protected fun addPlayerSlots() {
            // inventory
            for (i in 0..2) {
                for (l in 0..8) {
                    addSlot(Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18))
                }
            }
            // hotbar
            for (i in 0..8) {
                addSlot(Slot(playerInventory, i, 8 + i * 18, 142))
            }
        }

//        protected fun drawInputOutputs(matrices: MatrixStack, io: InputOutput) {
//            val input = 177 to 91
//            val output = 177 to 101
//            val w = 161 - 153
//            val h = 12 - 4
//            if (io.inputs.second.any { it is Faces.Top }) {
//                drawTexture(matrices, x + 153, y + 4, input.first, input.second, w, h)
//            }
//            if (io.inputs.second.any { it is Faces.Sides && it.left }) {
//                drawTexture(matrices, x + 143, y + 14, input.first, input.second, w, h)
//            }
//            if (io.inputs.second.any { it is Faces.Sides && it.right }) {
//                drawTexture(matrices, x + 163, y + 14, input.first, input.second, w, h)
//            }
//            if (io.outputs.second.any { it is Faces.Bottom }) {
//                drawTexture(matrices, x + 153, y + 24, output.first, output.second, w, h)
//            }
//            if (io.outputs.second.any { it is Faces.Back }) {
//                drawTexture(matrices, x + 163, y + 24, output.first, output.second, w, h)
//            }
//        }

        /**
         * Thanks to Kaupenjoe
         * Link: https://www.youtube.com/c/TKaupenjoe
         * Link: https://fabricmc.net/wiki/tutorial:containers
         * Link: https://github.com/FabricMC/yarn/issues/2944
         */
        override fun quickMove(player: PlayerEntity, i: Int): ItemStack {
            val slot = slots.getOrNull(i)?.takeIf { it.hasStack() } ?: return ItemStack.EMPTY
            val stack = slot.stack.copy()
            if (i < sidedInventory.size()) {
                if (!insertItem(slot.stack, sidedInventory.size(), slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!insertItem(slot.stack, 0, sidedInventory.size(), false)) {
                return ItemStack.EMPTY
            }
            slot.markDirty()
            return stack
        }

        override fun canUse(player: PlayerEntity): Boolean =
            sidedInventory.canPlayerUse(player)

    }

}

