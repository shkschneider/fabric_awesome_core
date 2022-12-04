package io.github.shkschneider.awesome.machines.recycler

import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.PropertyDelegate

class RecyclerScreenHandler : AwesomeBlockScreen.Handler {

    constructor(syncId: Int, blockEntity: RecyclerBlockEntity, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
        Recycler.screen, syncId, blockEntity, playerInventory, properties) {
    }
    constructor(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
        Recycler.screen, syncId, sidedInventory, playerInventory, properties)

    internal var entity: RecyclerBlockEntity? = null

    val progress: Int get() = entity?.progress ?: 0
    val duration: Int get() = entity?.duration ?: 0

    init {
        addProperties(properties)
        addSlots(
            30 + 4 to 31 + 4,
            92 to 17, 110 to 17, 128 to 17,
            92 to 35, 110 to 35, 128 to 35,
            92 to 53, 110 to 53, 128 to 53,
        )
        addPlayerSlots()
    }

}
