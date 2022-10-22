package io.github.shkschneider.awesome

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class AwesomeItem(
    private val id: String,
    private val itemGroup: ItemGroup = ItemGroup.MISC,
) {

    fun register() = Registry.register(Registry.ITEM, Identifier(Awesome.ID, id), Item(
        FabricItemSettings().group(itemGroup)
    ))

}