package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.core.AwesomeBlockEntity
import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandlerType

abstract class AwesomeMachineScreenHandler<BE : AwesomeBlockEntity.WithInventory> : AwesomeBlockScreen.Handler {

    constructor(screen: ScreenHandlerType<out AwesomeBlockScreen.Handler>, syncId: Int, blockEntity: BE, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
        screen, syncId, blockEntity as SidedInventory, playerInventory, properties
    )
    constructor(screen: ScreenHandlerType<out AwesomeBlockScreen.Handler>, syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
        screen, syncId, sidedInventory, playerInventory, properties
    )

    val progress: Int get() = getProperty(0)
    val duration: Int get() = getProperty(1)
    val fuel: Int get() = getProperty(2)

    fun getCustomProperty(index: Int): Int =
        getProperty(AwesomeMachine.PROPERTIES + index)

    /*
    init {
        addSlots()
        addPlayerSlots()
    }
    */

}
