package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.custom.MachinePorts
import io.github.shkschneider.awesome.custom.Minecraft
import io.github.shkschneider.awesome.custom.SimpleSidedInventory
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.BlockItem
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.state.property.Properties
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

abstract class AwesomeMachine<B : AwesomeMachineBlock<out AwesomeMachineBlockEntity>, BE : AwesomeMachineBlockEntity, SH : AwesomeMachineBlockScreen.Handler>(
    val id: Identifier,
    val ports: MachinePorts,
    blockProvider: () -> B,
    blockEntityProvider: (BlockPos, BlockState) -> BE,
    screenProvider: (SH, PlayerInventory, Text) -> HandledScreen<SH>,
    screenHandlerProvider: (Int, SidedInventory, PlayerInventory, ArrayPropertyDelegate) -> SH,
) : BlockEntityTicker<BE> {

    val block: B =
        Registry.register(Registry.BLOCK, id, blockProvider())

    val entityType: BlockEntityType<BE> = Registry.register(
        Registry.BLOCK_ENTITY_TYPE, id,
        FabricBlockEntityTypeBuilder.create(blockEntityProvider, this.block).build(null)
    )

    private var _entity: BE? = null
    val entity get() = _entity

    private lateinit var _screen: ScreenHandlerType<SH>
    val screen get() = _screen

    init {
        AwesomeRegistries.item(id, BlockItem(block, FabricItemSettings().group(Awesome.GROUP)))
        if (Minecraft.isClient) {
            _screen = ScreenHandlerType { syncId, playerInventory ->
                screenHandlerProvider(syncId, SimpleSidedInventory(ports.size), playerInventory, ArrayPropertyDelegate(AwesomeMachineBlockEntity.PROPERTIES))
            }
            HandledScreens.register(screen) { handler, playerInventory, title ->
                screenProvider(handler, playerInventory, title)
            }
        }
    }

    // TODO sounds
    protected fun on(blockEntity: BE, time: Int) = with(blockEntity) {
        duration = time
        progress = 0
        setPropertyState(Properties.LIT, true)
    }

    // TODO sounds
    protected fun off(blockEntity: BE) = with(blockEntity) {
        duration = 0
        progress = 0
        setPropertyState(Properties.LIT, false)
    }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: BE) {
        if (world.isClient) return
//        _handler._blockEntity = blockEntity
//        val powered = blockEntity.power > 0
//        blockEntity.setPropertyState(Properties.POWERED, powered)
//        if (powered.not()) off(blockEntity)
//        blockEntity.setPropertyState(Properties.LIT, blockEntity.duration > 0)
    }

}
