package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.AwesomeUtils
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.BlockItem
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.registry.Registry

object Smelter {

    const val ID = "smelter"
    val SLOTS = 2 to 1

    internal enum class Properties(val time: Int) {
        InputProgress(200),
        OutputProgress(20),
    }

    val BLOCK: SmelterBlock = Registry.register(
        Registry.BLOCK, AwesomeUtils.identifier(ID),
        SmelterBlock(FabricBlockSettings.copyOf(Blocks.BLAST_FURNACE))
    )

    val ENTITY: BlockEntityType<SmelterBlockEntity> = Registry.register(
        Registry.BLOCK_ENTITY_TYPE, AwesomeUtils.identifier(ID),
        FabricBlockEntityTypeBuilder.create(
            { pos, state -> SmelterBlockEntity(pos, state) },
            BLOCK
        ).build(null)
    )

    val SCREEN: ScreenHandlerType<SmelterScreenHandler> = ScreenHandlerType { syncId, playerInventory ->
        SmelterScreenHandler(syncId, playerInventory, SimpleInventory(SLOTS.first + SLOTS.second), ArrayPropertyDelegate(Properties.values().size))
    }

    operator fun invoke() {
        Registry.register(
            Registry.ITEM, AwesomeUtils.identifier(ID),
            BlockItem(BLOCK, FabricItemSettings().group(Awesome.GROUP))
        )
        HandledScreens.register(SCREEN) { handler, playerInventory, title ->
            SmelterScreen(handler, playerInventory, title)
        }
    }

}