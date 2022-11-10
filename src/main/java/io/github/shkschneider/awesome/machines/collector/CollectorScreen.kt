package io.github.shkschneider.awesome.machines.collector

import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachines
import io.github.shkschneider.awesome.machines.crafter.Crafter
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.text.Text

class CollectorScreen(
    name: String,
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeBlockScreen<CollectorScreen.Handler>(name, handler, playerInventory, title) {

    @Suppress("USELESS_CAST")
    class Handler(
        syncId: Int,
        inventories: InputOutput.Inventories,
        properties: PropertyDelegate,
    ) : AwesomeBlockScreen.Handler(
        AwesomeMachines.collector.screen as ScreenHandlerType<Handler>, syncId, inventories, properties
    ) {

        init {
            addProperties(properties)
            // 3x3 outputs
            addSlot(Slot(inventories.internal, 0, 62, 17))
            addSlot(Slot(inventories.internal, 1, 80, 17))
            addSlot(Slot(inventories.internal, 2, 98, 17))
            addSlot(Slot(inventories.internal, 3, 62, 35))
            addSlot(Slot(inventories.internal, 4, 80, 35))
            addSlot(Slot(inventories.internal, 5, 98, 35))
            addSlot(Slot(inventories.internal, 6, 62, 53))
            addSlot(Slot(inventories.internal, 7, 80, 53))
            addSlot(Slot(inventories.internal, 8, 98, 53))
            addPlayerSlots()
        }

        override fun onSlotClick(slotIndex: Int, button: Int, actionType: SlotActionType, player: PlayerEntity) {
            if (slotIndex in 0 until Crafter.SLOTS.inputs) {
                {}
            } else {
                super.onSlotClick(slotIndex, button, actionType, player)
            }
        }

    }

}
