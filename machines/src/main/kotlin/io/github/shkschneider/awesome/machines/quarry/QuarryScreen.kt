package io.github.shkschneider.awesome.machines.quarry

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.core.AwesomeColors
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.text.Text

@Suppress("RemoveRedundantQualifierName")
class QuarryScreen(
    machine: AwesomeMachine<QuarryBlock.Entity, QuarryScreen.Handler>,
    handler: QuarryScreen.Handler,
    playerInventory: PlayerInventory,
    title: Text?,
) : AwesomeMachineScreen<QuarryBlock.Entity, QuarryScreen.Handler>(machine, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        if (handler.progress > 0) {
            val progress = handler.progress * 55 / handler.duration
            drawTexture(matrices, x + 8, y + 7 + 55 - progress - 1, 176, 111, 192 - 176, progress + 1)
        }
        drawTextWithShadow(matrices, textRenderer, Text.of("${handler.efficiency * handler.fortune}/s"), x + 26, y + 54, AwesomeColors.white)
    }

    class Handler : AwesomeMachineScreenHandler<QuarryBlock.Entity> {

        constructor(syncId: Int, blockEntity: QuarryBlock.Entity, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
            AwesomeMachines.quarry.screen, syncId, blockEntity, playerInventory, properties
        )
        constructor(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
            AwesomeMachines.quarry.screen, syncId, sidedInventory, playerInventory, properties)

        val efficiency: Int get() = properties.get(2)
        val fortune: Int get() = properties.get(3)

        init {
            addProperties(properties)
            addSlots(
                62 to 35,
                98 to 35,
            )
            addPlayerSlots()
        }

    }

}
