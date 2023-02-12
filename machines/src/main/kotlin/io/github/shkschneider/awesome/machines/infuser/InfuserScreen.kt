package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.custom.SimpleSidedInventory
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.text.Text
import kotlin.math.roundToInt

@Suppress("RemoveRedundantQualifierName")
class InfuserScreen(
    machine: AwesomeMachine<InfuserBlock.Entity, InfuserScreen.Handler>,
    handler: InfuserScreen.Handler,
    playerInventory: PlayerInventory,
    title: Text?,
) : AwesomeMachineScreen<InfuserBlock.Entity, InfuserScreen.Handler>(machine, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        if (handler.progress > 0) {
            val progress = ((handler.progress.toFloat() / handler.duration.toFloat()) * 24.0).roundToInt()
            drawTexture(matrices, x + 84, y + 23 - 1, 176, 32, progress, 68 - 32)
        }
    }

    class Handler(
        machine: AwesomeMachine<InfuserBlock.Entity, InfuserScreen.Handler>,
        type: ScreenHandlerType<InfuserScreen.Handler>?,
        syncId: Int,
        playerInventory: PlayerInventory,
        sidedInventory: SidedInventory = SimpleSidedInventory(machine.io.size),
        properties: PropertyDelegate = ArrayPropertyDelegate(machine.properties),
    ) : AwesomeMachineScreenHandler<InfuserBlock.Entity>(
        type, syncId, playerInventory, sidedInventory, properties
    ) {
        
        init {
            addSlots(
                66 to 16,
                66 to 50,
                114 + 4 to 31 + 4,
            )
            addPlayerSlots()
        }

    }

}
