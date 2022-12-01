package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.PropertyDelegate

class GeneratorScreenHandler : AwesomeBlockScreen.Handler {

    constructor(syncId: Int, blockEntity: GeneratorBlockEntity, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(Generator.screen, syncId, blockEntity, playerInventory, properties) {
        this.power = blockEntity.power
    }
    constructor(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(Generator.screen, syncId, sidedInventory, playerInventory, properties)

    internal var entity: GeneratorBlockEntity? = null

    var power: Long = 0
    val progress: Int get() = entity?.progress ?: 0
    val duration: Int get() = entity?.duration ?: 0

    init {
        addProperties(properties)
        addSlots(
            80 to 53,
        )
        addPlayerSlots()
    }

}
