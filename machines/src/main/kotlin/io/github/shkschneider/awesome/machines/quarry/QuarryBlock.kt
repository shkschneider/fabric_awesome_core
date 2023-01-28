package io.github.shkschneider.awesome.machines.quarry

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.Minecraft
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
import net.minecraft.util.math.Direction

@Suppress("RemoveRedundantQualifierName")
class QuarryBlock(
    machine: AwesomeMachine<QuarryBlock.Entity, QuarryScreen.Handler>
) : AwesomeMachineBlock<QuarryBlock.Entity, QuarryScreen.Handler>(
    machine = machine,
    settings = FabricBlockSettings.copyOf(Blocks.FURNACE),
) {

    override fun tooltips(stack: ItemStack): List<Text> = listOf(
        Text.translatable(AwesomeUtils.translatable("block", machine.id, "hint")).formatted(Formatting.GRAY),
    )

    override fun blockEntity(machine: AwesomeMachine<QuarryBlock.Entity, QuarryScreen.Handler>, pos: BlockPos, state: BlockState): QuarryBlock.Entity =
        Entity(machine, pos, state)

    class Entity(
        machine: AwesomeMachine<QuarryBlock.Entity, QuarryScreen.Handler>,
        pos: BlockPos,
        state: BlockState,
    ) : AwesomeMachineBlockEntity<QuarryBlock.Entity, QuarryScreen.Handler>(
        machine, pos, state,
    ) {

        var efficiency: Int
            get() = getCustomProperty(0)
            set(value) = setCustomProperty(0, value)
        var fortune: Int
            get() = getCustomProperty(1)
            set(value) = setCustomProperty(1, value)

        init {
            efficiency = 1
            fortune = 1
            duration = Minecraft.TICKS / efficiency
        }

        override fun canInsert(slot: Int, stack: ItemStack, dir: Direction?): Boolean {
            return if (stack.item == AwesomeMachines.fuel) {
                slot == io.inputs - 1
            } else {
                super.canInsert(slot, stack, dir)
            }
        }

        override fun screen(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate): ScreenHandler =
            QuarryScreen.Handler(syncId, sidedInventory, playerInventory, properties)

    }

}
