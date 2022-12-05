package io.github.shkschneider.awesome.extras.crate

import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.screen.PropertyDelegate

class CrateBlockScreenHandler(
    syncId: Int, sidedInventory: Inventory, playerInventory: PlayerInventory, properties: PropertyDelegate,
) : AwesomeBlockScreen.Handler(
    Crate.screen, syncId, sidedInventory, playerInventory, properties,
) {

    init {
        addProperties(properties)
        addSlots(
            62 to 17, 80 to 17, 98 to 17,
            62 to 35, 80 to 35, 98 to 35,
            62 to 53, 80 to 53, 98 to 53
        )
        addPlayerSlots()
    }

}
