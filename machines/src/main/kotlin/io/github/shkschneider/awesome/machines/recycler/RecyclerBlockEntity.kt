package io.github.shkschneider.awesome.machines.recycler

import io.github.shkschneider.awesome.core.AwesomeBlockEntity
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos

class RecyclerBlockEntity(
    pos: BlockPos,
    state: BlockState,
) : AwesomeBlockEntity.WithInventory(Recycler.ID, Recycler.block.entityType, pos, state, Recycler.PORTS, Recycler.PROPERTIES to 0), AwesomeBlockEntity.WithScreen {

    private val _properties = mutableMapOf<Int, Int>()
    private val _delegate = object: PropertyDelegate {
        override fun get(index: Int): Int = _properties.getValue(index)
        override fun set(index: Int, value: Int) { _properties[index] = value }
        override fun size(): Int = _properties.size
    }

    var progress: Int
        get() = _delegate.get(0)
        set(value) = _delegate.set(0, value)
    var duration: Int
        get() = _delegate.get(1)
        set(value) = _delegate.set(1, value)

    override fun getDisplayName(): Text =
        Text.translatable(AwesomeUtils.translatable("block", Recycler.ID))

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ScreenHandler =
        RecyclerScreenHandler(syncId, this, playerInventory, _delegate)

}
