package io.github.shkschneider.awesome.machines.refinery

import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text

class RefineryScreen(
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineScreen<RefineryScreen.Handler>(Refinery.ID, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        if (handler.inputProgress > 0) {
            val progress = handler.inputProgress * 13 / Refinery.Process.Input.time
            drawTexture(matrices, x + 56, y + 36 + 12 - progress, 176, 12 - progress, 14, progress + 1)
        }
        if (handler.outputProgress > 0) {
            val progress = (Refinery.Process.Output.time - handler.outputProgress) * 24 / Refinery.Process.Output.time
            drawTexture(matrices, x + 79, y + 34, 176, 14, progress + 1, 16)
        }
    }

    @Suppress("USELESS_CAST")
    class Handler(
        syncId: Int,
        playerInventory: PlayerInventory,
        inventory: Inventory,
        properties: PropertyDelegate,
    ) : AwesomeMachineScreenHandler(
        AwesomeMachines.refinery.screen as ScreenHandlerType<Handler>, syncId, playerInventory, inventory, properties
    ) {

        init {
            addProperties(properties)
            addSlot(Slot(inventory, 0, 56, 17))
            addSlot(Slot(inventory, 1, 56, 53))
            addSlot(Slot(inventory, 2, 112 + 4, 31 + 4))
            addPlayerSlots()
        }

        override fun canInsertIntoSlot(stack: ItemStack, slot: Slot): Boolean {
            return super.canInsertIntoSlot(stack, slot)
        }

    }

}