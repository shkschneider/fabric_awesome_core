package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import io.github.shkschneider.awesome.machines.AwesomeMachineTicker
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text

class CrusherScreen(
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineScreen<CrusherScreen.Handler>(Crusher.ID, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        if (handler.inputProgress > 0) {
            val progress = handler.inputProgress * 13 / AwesomeMachineTicker.INPUT
            drawTexture(matrices, x + 57 - 1, y + 37 + (49 - 37) - progress - 1, 176, 13 - progress - 1, 189 - 176, progress + 1)
        }
        if (handler.outputProgress > 0) {
            val progress = (AwesomeMachineTicker.OUTPUT - handler.outputProgress) * 24 / AwesomeMachineTicker.OUTPUT
            drawTexture(matrices, x + 80 - 1, y + 36 - 1, 176, 15, progress + 1, 30 - 15)
        }
    }

    @Suppress("USELESS_CAST")
    class Handler(
        syncId: Int,
        playerInventory: PlayerInventory,
        inventory: Inventory,
        properties: PropertyDelegate,
    ) : AwesomeMachineScreenHandler(
        AwesomeMachines.crusher.screen as ScreenHandlerType<Handler>, syncId, playerInventory, inventory, properties
    ) {

        init {
            addProperties(properties)
            addSlot(Slot(inventory, 0, 56, 17)) // input
            addSlot(Slot(inventory, 1, 56, 53)) // fuel
            addSlot(Slot(inventory, 2, 112 + 4, 31 + 4)) // output
            addPlayerSlots()
        }

        override fun canInsertIntoSlot(stack: ItemStack, slot: Slot): Boolean {
            return super.canInsertIntoSlot(stack, slot)
        }

    }

}