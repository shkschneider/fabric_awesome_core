package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.custom.InputOutput
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
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.BlockItem
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry

abstract class AwesomeMachine<B : Block, BE : BlockEntity, SH : ScreenHandler>(
    id: Identifier,
    slots: InputOutput.Slots,
    blockProvider: () -> B,
    blockEntityProvider: (BlockPos, BlockState) -> BE,
    private val screenProvider: (SH, PlayerInventory, Text) -> HandledScreen<SH>,
    private val screenHandlerProvider: (Int, InputOutput.Inventories, ArrayPropertyDelegate) -> SH,
) : BlockEntityTicker<BE> {

    val block: B =
        Registry.register(Registry.BLOCK, id, blockProvider())

    val entityType: BlockEntityType<BE> = Registry.register(
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
                screenHandlerProvider(syncId, InputOutput.Inventories(SimpleInventory(slots.size), playerInventory), ArrayPropertyDelegate(2))
            }
            HandledScreens.register(this.screen) { handler, inventory, title ->
                screenProvider(handler, inventory, title)
            }
        }
    }

}
