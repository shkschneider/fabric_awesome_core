package io.github.shkschneider.awesome.machines.infuser

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
class InfuserBlock(
    machine: AwesomeMachine<InfuserBlock.Entity, InfuserScreen.Handler>
) : AwesomeMachineBlock<InfuserBlock.Entity, InfuserScreen.Handler>(
    machine = machine,
    settings = FabricBlockSettings.copyOf(Blocks.FURNACE),
) {

    override fun tooltips(stack: ItemStack): List<Text> = listOf(
        Text.translatable(AwesomeUtils.translatable("block", machine.id, "hint")).formatted(Formatting.GRAY),
    )

    override fun blockEntity(machine: AwesomeMachine<InfuserBlock.Entity, InfuserScreen.Handler>, pos: BlockPos, state: BlockState): InfuserBlock.Entity =
        InfuserBlock.Entity(machine, pos, state)

    class Entity(
        machine: AwesomeMachine<InfuserBlock.Entity, InfuserScreen.Handler>,
        pos: BlockPos,
        state: BlockState,
    ) : AwesomeMachineBlockEntity<InfuserBlock.Entity, InfuserScreen.Handler>(
        machine, pos, state,
    ) {

        override fun screenHandler(syncId: Int, playerInventory: PlayerInventory, sidedInventory: SidedInventory, properties: PropertyDelegate): ScreenHandler =
            InfuserScreen.Handler(machine, machine.screen, syncId, playerInventory, sidedInventory, properties)

    }

}
