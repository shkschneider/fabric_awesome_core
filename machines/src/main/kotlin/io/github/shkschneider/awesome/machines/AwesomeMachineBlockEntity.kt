package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.core.AwesomeBlockEntity
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.Minecraft
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

abstract class AwesomeMachineBlockEntity<BE : AwesomeBlockEntity.WithInventory, SH : AwesomeMachineScreenHandler<BE>>(
    protected val machine: AwesomeMachine<BE, SH>,
    pos: BlockPos,
    state: BlockState,
) : AwesomeBlockEntity.WithInventory(
    id = machine.id,
    type = machine.block.entityType,
    pos = pos,
    state = state,
    io = machine.io,
    delegates = machine.properties to 0,
), AwesomeBlockEntity.WithScreen {

    var progress: Int
        get() = properties.get(0)
        set(value) = properties.set(0, value)
    var duration: Int
        get() = properties.get(1)
        set(value) = properties.set(1, value)

    init {
        progress = 0
        duration = Minecraft.TICKS
    }

    override fun getDisplayName(): Text =
        Text.translatable(AwesomeUtils.translatable("block", machine.id))

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ScreenHandler =
        screen(syncId, this as SidedInventory, playerInventory, properties)

    abstract fun screen(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate): ScreenHandler

    override fun canInsert(slot: Int, stack: ItemStack, dir: Direction?): Boolean =
        io.isInput(slot)

    override fun canExtract(slot: Int, stack: ItemStack, dir: Direction?): Boolean =
        io.isOutput(slot)

}
