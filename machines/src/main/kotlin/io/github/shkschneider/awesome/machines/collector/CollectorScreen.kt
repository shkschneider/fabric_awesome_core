package io.github.shkschneider.awesome.machines.collector

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockScreen
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.text.Text

class CollectorScreen(
    name: String,
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineBlockScreen<CollectorScreen.Handler>(name, handler, playerInventory, title) {

    class Handler(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate) : AwesomeMachineBlockScreen.Handler(
        AwesomeMachines.collector.screen, syncId, sidedInventory, playerInventory, properties
    ) {

        init {
            addProperties(properties)
            addSlots(
                62 to 17, 80 to 17, 98 to 17,
                62 to 35, 80 to 35, 98 to 35,
                62 to 53, 80 to 53, 98 to 53,
            )
            addPlayerSlots()
        }

        override fun onSlotClick(slotIndex: Int, button: Int, actionType: SlotActionType, player: PlayerEntity) {
            if (slotIndex in 0 until Collector.IO.inputs.first) {
                {}
            } else {
                super.onSlotClick(slotIndex, button, actionType, player)
            }
        }

    }

}
