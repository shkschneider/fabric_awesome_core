package io.github.shkschneider.awesome.machines.collector

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.text.Text

@Suppress("RemoveRedundantQualifierName")
class CollectorScreen(
    machine: AwesomeMachine<CollectorBlock.Entity, CollectorScreen.Handler>,
    handler: CollectorScreen.Handler,
    playerInventory: PlayerInventory,
    title: Text?,
) : AwesomeMachineScreen<CollectorBlock.Entity, CollectorScreen.Handler>(machine, handler, playerInventory, title) {

    class Handler : AwesomeMachineScreenHandler<CollectorBlock.Entity> {

        constructor(syncId: Int, blockEntity: CollectorBlock.Entity, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
            AwesomeMachines.collector.screen, syncId, blockEntity, playerInventory, properties
        )
        constructor(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
            AwesomeMachines.collector.screen, syncId, sidedInventory, playerInventory, properties)

        private val machine: AwesomeMachine<CollectorBlock.Entity, CollectorScreen.Handler> get() = AwesomeMachines.collector

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
            if (slotIndex in 0 until machine.io.inputs.first) {
                {}
            } else {
                super.onSlotClick(slotIndex, button, actionType, player)
            }
        }

    }

}
