package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry

abstract class AwesomeMachine<B : Block, E : BlockEntity, SH : ScreenHandler>(
    id: Identifier,
    slots: Slots,
    blockProvider: () -> B,
    blockEntityProvider: (BlockPos, BlockState) -> E,
    screenProvider: (SH, PlayerInventory, Text) -> HandledScreen<SH>,
    screenHandlerProvider: (Int, PlayerInventory, Inventory, ArrayPropertyDelegate) -> SH,
) : BlockEntityTicker<E> {

    val block: B =
        Registry.register(Registry.BLOCK, id, blockProvider())

    val entityType: BlockEntityType<E> = Registry.register(
        Registry.BLOCK_ENTITY_TYPE, id,
        FabricBlockEntityTypeBuilder.create(blockEntityProvider, this.block).build(null)
    )

    val screen: ScreenHandlerType<SH> = ScreenHandlerType { syncId, playerInventory ->
        screenHandlerProvider(syncId, playerInventory, SimpleInventory(slots.size), ArrayPropertyDelegate(2))
    }

    init {
        Registry.register(
            Registry.ITEM, id,
            BlockItem(this.block, FabricItemSettings().group(Awesome.GROUP))
        )
        HandledScreens.register(this.screen) { handler, inventory, title ->
            screenProvider(handler, inventory, title)
        }
    }

    class Slots(
        val inputs: Int,
        val fuel: ItemStack? = ItemStack(AwesomeMaterials.redstoneFlux, 1),
        val outputs: Int = 1,
    ) {

        val size: Int = inputs + (if (fuel != null) 1 else 0) + outputs

    }

}
