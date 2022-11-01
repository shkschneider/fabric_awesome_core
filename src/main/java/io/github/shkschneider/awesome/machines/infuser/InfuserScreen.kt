package io.github.shkschneider.awesome.machines.infuser

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

class InfuserScreen(
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineScreen<InfuserScreen.Handler>(Infuser.ID, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        if (handler.outputProgress > 0) {
            val progress = (Infuser.Process.Output.time - handler.outputProgress) * 24 / Infuser.Process.Output.time
            drawTexture(matrices, x + 102, y + 47, 176, 14, progress + 1, 16)
        }
    }

    @Suppress("USELESS_CAST")
    class Handler(
        syncId: Int,
        playerInventory: PlayerInventory,
        inventory: Inventory,
        properties: PropertyDelegate,
    ) : AwesomeMachineScreenHandler(
        AwesomeMachines.infuser.screen as ScreenHandlerType<Handler>, syncId, playerInventory, inventory, properties
    ) {

        init {
            addProperties(properties)
            addSlot(Slot(inventory, 0, 27, 47))
            addSlot(Slot(inventory, 1, 76, 47))
            addSlot(Slot(inventory, 2, 134, 47))
            addPlayerSlots()
        }

        override fun canInsertIntoSlot(stack: ItemStack, slot: Slot): Boolean {
            return super.canInsertIntoSlot(stack, slot)
        }

    }

}