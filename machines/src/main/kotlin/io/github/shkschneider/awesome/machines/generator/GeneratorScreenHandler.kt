package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.PropertyDelegate

class GeneratorScreenHandler(
    syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate,
) : AwesomeBlockScreen.Handler(
    Generator.screen, syncId, sidedInventory, playerInventory, properties,
) {

    val power: Int get() = properties.get(0)
    val progress: Int get() = properties.get(1)
    val duration: Int get() = properties.get(2)

    init {
        addProperties(properties)
        addSlots(
            80 to 53,
        )
        addPlayerSlots()
    }

}
