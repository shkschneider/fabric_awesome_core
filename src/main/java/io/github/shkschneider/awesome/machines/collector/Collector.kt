package io.github.shkschneider.awesome.machines.collector

import io.github.shkschneider.awesome.Awesome
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.BlockItem
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object Collector {

    const val ID = "collector"

    val IO = 9

    val BLOCK = Registry.register(
        Registry.BLOCK, Identifier(Awesome.ID, ID),
        CollectorBlock(FabricBlockSettings.copyOf(Blocks.OBSIDIAN))
    ).also { block ->
        Registry.register(
            Registry.ITEM, Identifier(Awesome.ID, ID),
            BlockItem(block, FabricItemSettings().group(Awesome.GROUP))
        )
    }

    val ENTITY: BlockEntityType<CollectorBlockEntity> = Registry.register(
        Registry.BLOCK_ENTITY_TYPE,
        Identifier(Awesome.ID, ID),
        FabricBlockEntityTypeBuilder.create(
            { pos, state -> CollectorBlockEntity(pos, state) },
            BLOCK
        ).build(null)
    )

    val SCREEN: ScreenHandlerType<CollectorScreenHandler> = ScreenHandlerType { syncId, playerInventory ->
        CollectorScreenHandler(syncId, playerInventory, SimpleInventory(IO))
    }

    operator fun invoke() {
        HandledScreens.register(SCREEN) { handler, inventory, title ->
            CollectorScreen(handler, inventory, title)
        }
    }

}