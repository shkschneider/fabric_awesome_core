package io.github.shkschneider.awesome.machines.collector

import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text

class CollectorScreen(
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineScreen<CollectorScreen.Handler>(Collector.ID, handler, playerInventory, title) {

    @Suppress("USELESS_CAST")
    class Handler(
        syncId: Int,
        playerInventory: PlayerInventory,
        inventory: Inventory,
        properties: PropertyDelegate,
    ) : AwesomeMachineScreenHandler(
        AwesomeMachines.collector.screen as ScreenHandlerType<Handler>, syncId, playerInventory, inventory, properties
    ) {

        init {
            addProperties(properties)
            addSlot(Slot(inventory, 0, 62, 17))
            addSlot(Slot(inventory, 1, 80, 17))
            addSlot(Slot(inventory, 2, 98, 17))
            addSlot(Slot(inventory, 3, 62, 35))
            addSlot(Slot(inventory, 4, 80, 35))
            addSlot(Slot(inventory, 5, 98, 35))
            addSlot(Slot(inventory, 6, 62, 53))
            addSlot(Slot(inventory, 7, 80, 53))
            addSlot(Slot(inventory, 8, 98, 53))
            addPlayerSlots()
        }

        override fun canInsertIntoSlot(stack: ItemStack, slot: Slot): Boolean {
            return super.canInsertIntoSlot(stack, slot)
        }

    }

}
