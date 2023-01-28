package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.text.Text
import kotlin.math.roundToInt

@Suppress("RemoveRedundantQualifierName")
class SmelterScreen(
    machine: AwesomeMachine<SmelterBlock.Entity, SmelterScreen.Handler>,
    handler: SmelterScreen.Handler,
    playerInventory: PlayerInventory,
    title: Text?,
) : AwesomeMachineScreen<SmelterBlock.Entity, SmelterScreen.Handler>(machine, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        if (handler.progress > 0) {
            val progress = ((handler.progress.toFloat() / handler.duration.toFloat()) * 24.0).roundToInt()
            drawTexture(matrices, x + 80 - 1, y + 36 - 1, 176, 15, progress, 30 - 15)
        }
    }

    class Handler : AwesomeMachineScreenHandler<SmelterBlock.Entity> {

        constructor(syncId: Int, blockEntity: SmelterBlock.Entity, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
            AwesomeMachines.smelter.screen, syncId, blockEntity, playerInventory, properties
        )
        constructor(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
            AwesomeMachines.smelter.screen, syncId, sidedInventory, playerInventory, properties)

        init {
            addProperties(properties)
            addSlots(
                56 to 35,
                112 + 4 to 31 + 4,
            )
            addPlayerSlots()
        }

    }

}
