package io.github.shkschneider.awesome.machines.crafter

import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.text.Text

class CrafterScreen(
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineScreen<CrafterScreen.Handler>(Crafter.ID, handler, playerInventory, title) {

    @Suppress("USELESS_CAST")
    class Handler(
        syncId: Int,
        playerInventory: PlayerInventory,
        inventory: Inventory,
        properties: PropertyDelegate,
    ) : AwesomeMachineScreenHandler(
        AwesomeMachines.crafter.screen as ScreenHandlerType<Handler>, syncId, playerInventory, inventory, properties
    ) {

        init {
            addProperties(properties)
            addSlot(Slot(inventory, 0, 30, 17))
            addSlot(Slot(inventory, 1, 48, 17))
            addSlot(Slot(inventory, 2, 66, 17))
            addSlot(Slot(inventory, 3, 30, 35))
            addSlot(Slot(inventory, 4, 48, 35))
            addSlot(Slot(inventory, 5, 66, 35))
            addSlot(Slot(inventory, 6, 30, 53))
            addSlot(Slot(inventory, 7, 48, 53))
            addSlot(Slot(inventory, 8, 66, 53))
            addSlot(Slot(inventory, 9, 120 + 4, 31 + 4))
            addPlayerSlots()
        }

        override fun onSlotClick(slotIndex: Int, button: Int, actionType: SlotActionType, player: PlayerEntity) {
            if (slotIndex in 0 until Crafter.SLOTS.inputs) {
                TODO()
            } else {
                super.onSlotClick(slotIndex, button, actionType, player)
            }
        }

        override fun canInsertIntoSlot(stack: ItemStack, slot: Slot): Boolean {
            return super.canInsertIntoSlot(stack, slot)
        }

    }

}
