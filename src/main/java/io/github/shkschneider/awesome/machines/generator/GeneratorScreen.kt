package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.machines.AwesomeMachines
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.text.Text

class GeneratorScreen(
    name: String,
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeBlockScreen<GeneratorScreen.Handler>(name, handler, playerInventory, title) {

    class Handler(
        syncId: Int,
        inventories: InputOutput.Inventories,
        properties: PropertyDelegate,
    ) : AwesomeBlockScreen.Handler(
        AwesomeMachines.generator.screen, syncId, inventories, properties
    ) {

        init {
            addProperties(properties)
            addSlots(
                80 to 53,
            )
            addPlayerSlots()
        }

    }

}
