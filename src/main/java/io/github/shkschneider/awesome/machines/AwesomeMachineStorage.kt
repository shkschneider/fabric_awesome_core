package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.AwesomeUtils
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage
import net.minecraft.util.math.Direction

class AwesomeMachineStorage(
    private val facing: Direction,
    private val canInsert: List<Pair<Direction, Boolean>>,
    private val canExtract: List<Pair<Direction, Boolean>>,
) : SingleVariantStorage<ItemVariant>() {

    override fun getBlankVariant(): ItemVariant =
        ItemVariant.blank()

    override fun getCapacity(variant: ItemVariant): Long =
        AwesomeUtils.STACK.toLong()

    fun canInsert(direction: Direction): Boolean =
        canInsert.firstOrNull { it.first == facing }?.second ?: false

    fun canExtract(direction: Direction): Boolean =
        canExtract.firstOrNull { it.first == facing }?.second ?: false

}
