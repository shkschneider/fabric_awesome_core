package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.core.AwesomeBlockEntity
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos

class GeneratorBlockEntity(
    pos: BlockPos, state: BlockState,
) : AwesomeBlockEntity.WithInventory(
    Generator.ID, Generator.block.entityType, pos, state, Generator.IO, Generator.PROPERTIES to 0,
), AwesomeBlockEntity.WithScreen {

    var power: Int
        get() = properties.get(0)
        set(value) = properties.set(0, value)
    var progress: Int
        get() = properties.get(1)
        set(value) = properties.set(1, value)
    var duration: Int
        get() = properties.get(2)
        set(value) = properties.set(2, value)

    override fun getDisplayName(): Text =
        Text.translatable(AwesomeUtils.translatable("block", Generator.ID))

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ScreenHandler =
        GeneratorScreenHandler(syncId, this as SidedInventory, playerInventory, properties)

}
