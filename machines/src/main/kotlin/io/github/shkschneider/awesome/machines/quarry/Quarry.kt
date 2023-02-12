package io.github.shkschneider.awesome.machines.quarry

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.core.ext.getEnchantmentLevel
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.custom.Minecraft
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.state.property.Properties
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.World

class Quarry : AwesomeMachine<QuarryBlock.Entity, QuarryScreen.Handler>(
    id = "quarry",
    io = InputOutput(inputs = 1, fueled = true, outputs = 1),
    properties = PROPERTIES + 2,
) {

    override fun block(): AwesomeMachineBlock<QuarryBlock.Entity, QuarryScreen.Handler> =
        QuarryBlock(this)

    override fun screenHandler(syncId: Int, playerInventory: PlayerInventory): QuarryScreen.Handler =
        QuarryScreen.Handler(this, null, syncId, playerInventory)

    override fun screen(handler: QuarryScreen.Handler, playerInventory: PlayerInventory, title: Text): AwesomeMachineScreen<QuarryBlock.Entity, QuarryScreen.Handler> =
        QuarryScreen(this, handler, playerInventory, title)

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: QuarryBlock.Entity) {
        fun on() {
            blockEntity.setPropertyState(state.with(Properties.LIT, true))
            blockEntity.markDirty()
        }
        fun off() {
            blockEntity.setPropertyState(state.with(Properties.LIT, false))
            blockEntity.progress = 0
            blockEntity.markDirty()
        }
        // shut off if no output room
        if (io.getOutputs(blockEntity as Inventory).all { it.second.count == it.second.maxCount }) {
            off()
            return
        }
        // if no fuel, burn some
        if (blockEntity.fuel == 0) {
            val (_, fuelStack) = io.getFuel(blockEntity as Inventory)
            if (fuelStack.count > 0) {
                fuelStack.count--
                blockEntity.fuel += AwesomeMachines.fuel.time
            } else {
                // otherwise shut off
                off()
                return
            }
        } else {
            on()
            blockEntity.fuel--
        }
        // ...
        blockEntity.progress++
        if (blockEntity.progress >= blockEntity.duration) {
            quarry(blockEntity, world.random)
        }
    }

    private fun quarry(blockEntity: QuarryBlock.Entity, random: Random) {
        val ores = listOf(
            // block to placed_feature.count * configured_feature.size
            Blocks.COAL_ORE to 20 * 17,
            Blocks.DIAMOND_ORE to 7 * 8,
            Blocks.EMERALD_ORE to 100 * 3,
            Blocks.LAPIS_ORE to 2 * 7,
            Blocks.COPPER_ORE to 16 * 15,
            Blocks.GOLD_ORE to 4 * 9,
            Blocks.IRON_ORE to 90 * 9,
            Blocks.REDSTONE_ORE to 4 * 8,
        )
        val r = random.nextBetween(0, ores.sumOf { it.second })
        var v = 0
        ores.forEach { ore ->
            v += ore.second
            if (v >= r) {
                blockEntity.progress = 0
                blockEntity.insert(ItemStack(ore.first, blockEntity.fortune))
                blockEntity.efficiency = 1 + io.getInputs(blockEntity).maxOf { it.second.getEnchantmentLevel(Enchantments.EFFICIENCY) }
                blockEntity.duration = Minecraft.TICKS / blockEntity.efficiency
                blockEntity.fortune = 1 + io.getInputs(blockEntity).maxOf { it.second.getEnchantmentLevel(Enchantments.FORTUNE) }
                return@forEach
            }
        }
    }

}
