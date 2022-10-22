package io.github.shkschneider.awesome

import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object AwesomeItems {

    fun register(id: String, item: Item): Item {
        return Registry.register(Registry.ITEM, Identifier(Awesome.ID, id), item)
    }

}