package io.github.shkschneider.awesome.core.ext

import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack

fun Inventory.getStacks(): List<ItemStack> =
    (0 until size()).map { getStack(it) }

fun Inventory.insert(external: ItemStack): ItemStack {
    getStacks().forEachIndexed { index, internal ->
        if (external.isEmpty) return@forEachIndexed
        if (internal.isEmpty) {
            setStack(index, external.copy())
        } else if (internal.item == external.item) {
            while (external.count > 0 && internal.count < internal.maxCount) {
                internal.count = internal.count + 1
                external.count = external.count - 1
           }
        }
    }
    markDirty()
    return external
}
