package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.core.AwesomeBlockEntity
import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text

abstract class AwesomeMachineScreen<BE : AwesomeBlockEntity.WithInventory, SH : AwesomeMachineScreenHandler<BE>>(
    protected val machine: AwesomeMachine<BE, SH>,
    handler: SH,
    playerInventory: PlayerInventory,
    text: Text?,
) : AwesomeBlockScreen<SH>(machine.id, handler, playerInventory, text ?: machine.block.name) {

    override fun init() {
        super.init()
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2
    }

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        setShader()
        // ...
    }

}
