package io.github.shkschneider.awesome.machines.quarry

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
            get() = properties.get(2)
            set(value) = properties.set(2, value)
        var fortune: Int
            get() = properties.get(3)
            set(value) = properties.set(3, value)

        init {
            efficiency = 1
            fortune = 1
            duration = Minecraft.TICKS / efficiency
        }

        override fun screen(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate): ScreenHandler =
            QuarryScreen.Handler(syncId, sidedInventory, playerInventory, properties)

        fun insert(stack: ItemStack) {
            while (stack.count > 0) {
                if (getStack(1).isEmpty) {
                    setStack(1, stack)
                    break
                }  else if (getStack(1).item == stack.item && getStack(1).count < getStack(1).maxCount) {
                    getStack(1).count++
                    stack.count--
                } else {
                    break
                }
            }
            markDirty()
        }

    }

}
