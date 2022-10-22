package io.github.shkschneider.awesome

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object AwesomeBlocks {

    fun register(id: String, group: ItemGroup, block: Block): Block {
        AwesomeItems.register(id, BlockItem(block, FabricItemSettings().group(group)))
        return Registry.register(Registry.BLOCK, Identifier(Awesome.ID, id), block)
    }

}