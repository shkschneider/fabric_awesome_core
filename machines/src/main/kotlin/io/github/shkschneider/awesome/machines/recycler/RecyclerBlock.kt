package io.github.shkschneider.awesome.machines.recycler

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
class RecyclerBlock(
    machine: AwesomeMachine<RecyclerBlock.Entity, RecyclerScreen.Handler>
) : AwesomeMachineBlock<RecyclerBlock.Entity, RecyclerScreen.Handler>(
    machine = machine,
    settings = FabricBlockSettings.copyOf(Blocks.FURNACE),
) {

    override fun tooltips(stack: ItemStack): List<Text> = listOf(
        Text.translatable(AwesomeUtils.translatable("block", machine.id, "hint")).formatted(Formatting.GRAY),
    )

    override fun blockEntity(machine: AwesomeMachine<RecyclerBlock.Entity, RecyclerScreen.Handler>, pos: BlockPos, state: BlockState): RecyclerBlock.Entity =
        RecyclerBlock.Entity(machine, pos, state)

    class Entity(
        machine: AwesomeMachine<RecyclerBlock.Entity, RecyclerScreen.Handler>,
        pos: BlockPos,
        state: BlockState,
    ) : AwesomeMachineBlockEntity<RecyclerBlock.Entity, RecyclerScreen.Handler>(
        machine, pos, state,
    ) {

        override fun screenHandler(syncId: Int, playerInventory: PlayerInventory, sidedInventory: SidedInventory, properties: PropertyDelegate): ScreenHandler =
            RecyclerScreen.Handler(machine, machine.screen, syncId, playerInventory, sidedInventory, properties)

    }

}
