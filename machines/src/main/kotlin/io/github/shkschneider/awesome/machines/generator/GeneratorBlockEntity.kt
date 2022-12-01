package io.github.shkschneider.awesome.machines.generator

import io.github.shkschneider.awesome.core.AwesomeBlockEntity
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos

class GeneratorBlockEntity(
    pos: BlockPos,
    state: BlockState,
) : AwesomeBlockEntity.WithInventory(Generator.NAME, Generator.block.entityType, pos, state, Generator.PORTS, Generator.PROPERTIES to 0), AwesomeBlockEntity.WithScreen {

    private val _properties = mutableMapOf<Int, Int>()
    private val _delegate = object: PropertyDelegate {
        override fun get(index: Int): Int = _properties.getValue(index)
        override fun set(index: Int, value: Int) { _properties[index] = value }
        override fun size(): Int = _properties.size
    }

    internal val energy = AwesomeMachinePower(this)
    var power: Long
        get() = energy.amount
        set(value) { energy.amount = value }

    var progress: Int
        get() = _delegate.get(0)
        set(value) = _delegate.set(0, value)
    var duration: Int
        get() = _delegate.get(1)
        set(value) = _delegate.set(1, value)

    override fun getDisplayName(): Text =
        Text.translatable(AwesomeUtils.translatable("block", Generator.NAME))

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ScreenHandler =
        GeneratorScreenHandler(syncId, this, playerInventory, _delegate)

}
