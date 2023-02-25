package io.github.shkschneider.awesome.extras.crates

import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.text.Text

@Suppress("RemoveRedundantQualifierName")
class CrateBlockScreen(
    handler: CrateBlockScreen.Handler, playerInventory: PlayerInventory, title: Text,
) : AwesomeBlockScreen<CrateBlockScreen.Handler>(
    Crates.ID, handler, playerInventory, title,
) {

    class Handler(
        type: ScreenHandlerType<CrateBlockScreen.Handler>?,
        syncId: Int,
        playerInventory: PlayerInventory,
        internalInventory: Inventory = SimpleInventory(9),
    ) : AwesomeBlockScreen.Handler(type, syncId, playerInventory, internalInventory) {

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

}
