package io.github.shkschneider.awesome.machines.quarry

import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.PropertyDelegate

class QuarryScreenHandler : AwesomeBlockScreen.Handler {

    constructor(syncId: Int, blockEntity: QuarryBlockEntity, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
        Quarry.screen, syncId, blockEntity, playerInventory, properties) {
    }
    constructor(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
        Quarry.screen, syncId, sidedInventory, playerInventory, properties)

    val progress: Int get() = properties.get(0)
    val duration: Int get() = properties.get(1)
    val efficiency: Int get() = properties.get(2)
    val fortune: Int get() = properties.get(3)

    init {
        addProperties(properties)
        addSlots(
            62 to 35,
            98 to 35,
        )
        addPlayerSlots()
    }

}
