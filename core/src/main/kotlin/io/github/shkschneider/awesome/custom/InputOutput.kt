package io.github.shkschneider.awesome.custom

import io.github.shkschneider.awesome.core.ext.getStacks
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack

data class InputOutput(
    val inputs: Int = 0,
    val fueled: Boolean = false,
    val outputs: Int = 0,
) {

    val size: Int = inputs + (if (fueled) 1 else 0) + outputs

    fun isInput(slot: Int): Boolean =
        slot in (0 until inputs)

    fun canInsert(slot: Int, face: Faces): Boolean =
        face == Faces.Top && ((inputs == 0 && outputs > 0) || isInput(slot))

    fun isFuel(slot: Int): Boolean =
        slot == inputs

    fun isOutput(slot: Int): Boolean =
        slot in ((inputs + if (fueled) 1 else 0) until size)

    fun canExtract(slot: Int, face: Faces): Boolean =
        face == Faces.Bottom && ((outputs == 0 && inputs > 0) || isOutput(slot))

    fun getInputs(inventory: Inventory): List<Pair<Int, ItemStack>> =
        inventory.getStacks().mapIndexed { slot, stack -> slot to stack }.filter { isInput(it.first) }

    fun getFuel(inventory: Inventory): Pair<Int, ItemStack> =
        inventory.getStacks().mapIndexed { slot, stack -> slot to stack }.first { isFuel(it.first) }

    fun getOutputs(inventory: Inventory): List<Pair<Int, ItemStack>> =
        inventory.getStacks().mapIndexed { slot, stack -> slot to stack }.filter { isOutput(it.first) }

    override fun toString(): String =
        "I/O: $inputs+${if (fueled) 1 else 0}+$outputs=$size"

}
