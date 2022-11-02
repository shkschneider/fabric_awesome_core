package io.github.shkschneider.awesome.machines.infuser

import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import io.github.shkschneider.awesome.machines.AwesomeMachineTicker
import io.github.shkschneider.awesome.machines.AwesomeMachines
import io.github.shkschneider.awesome.machines.smelter.Smelter
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
        if (handler.inputProgress > 0) {
            val progress = handler.inputProgress * 13 / AwesomeMachineTicker.INPUT
            drawTexture(matrices, x + 19 - 1, y + 46 + (49 - 37) - progress - 1, 176, 13 - progress - 1, 189 - 176, progress + 1)
        }
        if (handler.outputProgress > 0) {
            val progress = (AwesomeMachineTicker.OUTPUT - handler.outputProgress) * 24 / AwesomeMachineTicker.OUTPUT
            drawTexture(matrices, x + 84, y + 23 - 1, 176, 32, progress + 1, 68 - 32)
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
            addSlot(Slot(inventory, 0, 66, 16)) // input
            addSlot(Slot(inventory, 1, 66, 50)) // input
            addSlot(Slot(inventory, 2, 18, 50)) // fuel
            addSlot(Slot(inventory, 2, 114 + 4, 31 + 4)) // output
            addPlayerSlots()
        }

        override fun canInsertIntoSlot(stack: ItemStack, slot: Slot): Boolean {
            return super.canInsertIntoSlot(stack, slot)
        }

    }

}