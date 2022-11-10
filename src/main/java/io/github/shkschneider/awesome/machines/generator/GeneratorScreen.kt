package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text

class GeneratorScreen(
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineScreen<GeneratorScreen.Handler>(Generator.ID, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        if (handler.inputProgress > 0) {
            drawTexture(matrices, x + 8, y + 7, 176, 111, 192 - 176, 166 - 111)
        }
    }

    @Suppress("USELESS_CAST")
    class Handler(
        syncId: Int,
        inventories: InputOutput.Inventories,
        properties: PropertyDelegate,
    ) : AwesomeMachineScreenHandler(
        AwesomeMachines.generator.screen as ScreenHandlerType<Handler>, syncId, inventories, properties
    ) {

        init {
            addProperties(properties)
            addSlot(Slot(inventories.internal, 0, 80, 53))
            addPlayerSlots()
        }

//        override fun onSlotClick(slotIndex: Int, button: Int, actionType: SlotActionType, player: PlayerEntity) {
//            if (slotIndex in 0 until Generator.SLOTS.inputs) {
//                TODO()
//            } else {
//                super.onSlotClick(slotIndex, button, actionType, player)
//            }
//        }

    }

}
