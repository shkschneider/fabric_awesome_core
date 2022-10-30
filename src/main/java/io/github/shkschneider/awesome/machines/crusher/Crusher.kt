package io.github.shkschneider.awesome.machines.crusher

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

object Crusher {

    const val ID = "crusher"
    val SLOTS = 1 to 1

    internal enum class Properties(val time: Int) {
        OutputProgress(20),
    }

    val BLOCK = Registry.register(
        Registry.BLOCK, AwesomeUtils.identifier(ID),
        CrusherBlock(FabricBlockSettings.copyOf(Blocks.FURNACE).luminance(0))
    )

    val ENTITY: BlockEntityType<CrusherBlockEntity> = Registry.register(
        Registry.BLOCK_ENTITY_TYPE, AwesomeUtils.identifier(ID),
        FabricBlockEntityTypeBuilder.create(
            { pos, state -> CrusherBlockEntity(pos, state) },
            BLOCK
        ).build(null)
    )

    val SCREEN: ScreenHandlerType<CrusherScreenHandler> = ScreenHandlerType { syncId, playerInventory ->
        CrusherScreenHandler(syncId, playerInventory, SimpleInventory(SLOTS.first + SLOTS.second), ArrayPropertyDelegate(Properties.values().size))
    }

    operator fun invoke() {
        Registry.register(
            Registry.ITEM, AwesomeUtils.identifier(ID),
            BlockItem(BLOCK, FabricItemSettings().group(Awesome.GROUP))
        )
        HandledScreens.register(SCREEN) { handler, inventory, title ->
            CrusherScreen(handler, inventory, title)
        }
        CrusherRecipes()
    }

}