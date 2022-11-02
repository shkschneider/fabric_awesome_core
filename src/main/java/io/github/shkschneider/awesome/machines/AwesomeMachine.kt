package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.fabricmc.api.EnvType
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
    private val slots: Slots,
    blockProvider: () -> B,
    blockEntityProvider: (BlockPos, BlockState) -> E,
    private val screenProvider: (SH, PlayerInventory, Text) -> HandledScreen<SH>,
    private val screenHandlerProvider: (Int, PlayerInventory, Inventory, ArrayPropertyDelegate) -> SH,
) : BlockEntityTicker<E> {

    val block: B =
        Registry.register(Registry.BLOCK, id, blockProvider())

    val entityType: BlockEntityType<E> = Registry.register(
        Registry.BLOCK_ENTITY_TYPE, id,
        FabricBlockEntityTypeBuilder.create(blockEntityProvider, this.block).build(null)
    )

    lateinit var _screen: ScreenHandlerType<SH>
    val screen get() = _screen

    init {
        Registry.register(
            Registry.ITEM, id,
            BlockItem(this.block, FabricItemSettings().group(Awesome.GROUP))
        )
        if (AwesomeUtils.environmentType() == EnvType.CLIENT) {
            _screen = ScreenHandlerType { syncId, playerInventory ->
                screenHandlerProvider(syncId, playerInventory, SimpleInventory(slots.size), ArrayPropertyDelegate(2))
            }
            HandledScreens.register(this.screen) { handler, inventory, title ->
                screenProvider(handler, inventory, title)
            }
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
