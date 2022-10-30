package io.github.shkschneider.awesome.materials

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.materials.redstone.RedstoneDust
import io.github.shkschneider.awesome.materials.redstone.RedstoneFlux
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object AwesomeMaterials {

    val redstoneDust = RedstoneDust()
    val redstoneFlux = RedstoneFlux()

    operator fun invoke() = Unit

    operator fun invoke(id: String, block: Block, group: ItemGroup = Awesome.GROUP) {
        invoke(id, BlockItem(block, FabricItemSettings().group(group)) as Item)
        Registry.register(Registry.BLOCK, Identifier(Awesome.ID, id), block)
    }

    operator fun invoke(id: String, item: Item) {
        Registry.register(Registry.ITEM, Identifier(Awesome.ID, id), item)
    }

}