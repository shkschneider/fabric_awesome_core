package io.github.shkschneider.awesome

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object AwesomeItemGroups {

    val AWESOME = register(Awesome.ID, ItemStack(AwesomeItems.register(Awesome.ID, Item(
        FabricItemSettings().group(ItemGroup.MISC)
    ))))

    fun register(id: String, itemStack: ItemStack): ItemGroup {
        return FabricItemGroupBuilder.build(Identifier(Awesome.ID, id)) { itemStack }
    }

    operator fun invoke() = Unit

}