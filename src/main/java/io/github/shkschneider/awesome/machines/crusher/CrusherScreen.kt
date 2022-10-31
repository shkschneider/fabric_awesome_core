package io.github.shkschneider.awesome.machines.crusher

import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text

class CrusherScreen(
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineScreen<CrusherScreen.Handler>(Crusher.ID, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        if (handler.outputProgress > 0) {
            val progress = (Crusher.Process.Output.time - handler.outputProgress) * 24 / Crusher.Process.Output.time
            drawTexture(matrices, x + 102, y + 47, 176, 14, progress + 1, 16)
        }
    }

    class Handler(
        syncId: Int,
        playerInventory: PlayerInventory,
        inventory: Inventory,
        properties: PropertyDelegate,
    ) : AwesomeMachineScreenHandler(
        AwesomeMachines.crusher.screen, syncId, playerInventory, inventory, properties
    ) {

        init {
            addProperties(properties)
            addSlot(Slot(inventory, 0, 76, 47))
            addSlot(Slot(inventory, 1, 134, 47))
            addPlayerSlots()
        }

        override fun canInsertIntoSlot(stack: ItemStack, slot: Slot): Boolean {
            return super.canInsertIntoSlot(stack, slot)
        }

    }

}