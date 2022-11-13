package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.core.Minecraft
import io.github.shkschneider.awesome.custom.InputOutput
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SimpleInventory
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
    id: Identifier,
    private val slots: InputOutput.Slots,
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

    private lateinit var _screen: ScreenHandlerType<SH>
    val screen get() = _screen

    init {
        AwesomeRegistries.item(id, BlockItem(block, FabricItemSettings().group(Awesome.GROUP)))
        if (Minecraft.isClient) {
            _screen = ScreenHandlerType { syncId, playerInventory ->
                // empty things to get sync'ed
                screenHandlerProvider(syncId, InputOutput.Inventories(SimpleInventory(slots.size), playerInventory), ArrayPropertyDelegate(AwesomeMachineBlockEntity.PROPERTIES))
            }
            HandledScreens.register(screen) { handler, inventory, title ->
                screenProvider(handler, inventory, title)
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
        blockEntity.power = world.getReceivedRedstonePower(pos)
        val powered = blockEntity.power > 0
        blockEntity.setPropertyState(Properties.POWERED, powered)
        if (powered.not()) off(blockEntity)
        blockEntity.setPropertyState(Properties.LIT, blockEntity.duration > 0)
    }

}
