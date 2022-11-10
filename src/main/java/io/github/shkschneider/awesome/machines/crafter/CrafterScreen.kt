package io.github.shkschneider.awesome.machines.crafter

import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
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
        inventories: InputOutput.Inventories,
        properties: PropertyDelegate,
    ) : AwesomeMachineScreenHandler(
        AwesomeMachines.crafter.screen as ScreenHandlerType<Handler>, syncId, inventories, properties
    ) {

        init {
            addProperties(properties)
            // TODO 1x5 inventory
            // 3x3 inputs
            addSlot(Slot(inventories.internal, 0, 30, 17))
            addSlot(Slot(inventories.internal, 1, 48, 17))
            addSlot(Slot(inventories.internal, 2, 66, 17))
            addSlot(Slot(inventories.internal, 3, 30, 35))
            addSlot(Slot(inventories.internal, 4, 48, 35))
            addSlot(Slot(inventories.internal, 5, 66, 35))
            addSlot(Slot(inventories.internal, 6, 30, 53))
            addSlot(Slot(inventories.internal, 7, 48, 53))
            addSlot(Slot(inventories.internal, 8, 66, 53))
            // 1x1 output
            addSlot(Slot(inventories.internal, 9, 120 + 4, 31 + 4))
            addPlayerSlots()
        }

        override fun onSlotClick(slotIndex: Int, button: Int, actionType: SlotActionType, player: PlayerEntity) {
            if (slotIndex in 0 until Crafter.SLOTS.inputs) {
                TODO()
            } else {
                super.onSlotClick(slotIndex, button, actionType, player)
            }
        }

    }

}
