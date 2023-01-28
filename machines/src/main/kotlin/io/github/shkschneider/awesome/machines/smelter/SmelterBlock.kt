package io.github.shkschneider.awesome.machines.smelter

import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockEntity
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.math.BlockPos

@Suppress("RemoveRedundantQualifierName")
class SmelterBlock(
    machine: AwesomeMachine<SmelterBlock.Entity, SmelterScreen.Handler>
) : AwesomeMachineBlock<SmelterBlock.Entity, SmelterScreen.Handler>(
    machine = machine,
    settings = FabricBlockSettings.copyOf(Blocks.FURNACE),
) {

    override fun tooltips(stack: ItemStack): List<Text> = listOf(
        Text.translatable(AwesomeUtils.translatable("block", machine.id, "hint")).formatted(Formatting.GRAY),
    )

    override fun blockEntity(machine: AwesomeMachine<SmelterBlock.Entity, SmelterScreen.Handler>, pos: BlockPos, state: BlockState): SmelterBlock.Entity =
        SmelterBlock.Entity(machine, pos, state)

    class Entity(
        machine: AwesomeMachine<SmelterBlock.Entity, SmelterScreen.Handler>,
        pos: BlockPos,
        state: BlockState,
    ) : AwesomeMachineBlockEntity<Entity, SmelterScreen.Handler>(
        machine, pos, state,
    ) {

        override fun screen(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate): ScreenHandler =
            SmelterScreen.Handler(syncId, sidedInventory, playerInventory, properties)

    }

}
