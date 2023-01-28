package io.github.shkschneider.awesome.machines.crusher

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
class CrusherBlock(
    machine: AwesomeMachine<CrusherBlock.Entity, CrusherScreen.Handler>
) : AwesomeMachineBlock<CrusherBlock.Entity, CrusherScreen.Handler>(
    machine = machine,
    settings = FabricBlockSettings.copyOf(Blocks.FURNACE),
) {

    override fun tooltips(stack: ItemStack): List<Text> = listOf(
        Text.translatable(AwesomeUtils.translatable("block", machine.id, "hint1")).formatted(Formatting.GRAY),
        Text.translatable(AwesomeUtils.translatable("block", machine.id, "hint2")).formatted(Formatting.GRAY),
        Text.translatable(AwesomeUtils.translatable("block", machine.id, "hint3")).formatted(Formatting.GRAY),
    )

    override fun blockEntity(machine: AwesomeMachine<CrusherBlock.Entity, CrusherScreen.Handler>, pos: BlockPos, state: BlockState): CrusherBlock.Entity =
        CrusherBlock.Entity(machine, pos, state)

    class Entity(
        machine: AwesomeMachine<CrusherBlock.Entity, CrusherScreen.Handler>,
        pos: BlockPos,
        state: BlockState,
    ) : AwesomeMachineBlockEntity<CrusherBlock.Entity, CrusherScreen.Handler>(
        machine, pos, state,
    ) {

        override fun screen(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate): ScreenHandler =
            CrusherScreen.Handler(syncId, sidedInventory, playerInventory, properties)

    }

}
