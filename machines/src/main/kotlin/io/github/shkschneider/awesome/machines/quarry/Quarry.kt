package io.github.shkschneider.awesome.machines.quarry

import io.github.shkschneider.awesome.core.ext.getEnchantmentLevel
import io.github.shkschneider.awesome.custom.Faces
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.custom.Minecraft
import io.github.shkschneider.awesome.custom.SimpleSidedInventory
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineBlock
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.ItemStack
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.World

class Quarry : AwesomeMachine<QuarryBlock.Entity, QuarryScreen.Handler>(
    id = "quarry",
    io = InputOutput(inputs = 1 to listOf(Faces.Top), outputs = 1 to listOf(Faces.Bottom)),
    properties = 4,
) {

    override fun block(): AwesomeMachineBlock<QuarryBlock.Entity, QuarryScreen.Handler> =
        QuarryBlock(this)

    override fun screen(): ScreenHandlerType<QuarryScreen.Handler> =
        ScreenHandlerType { syncId, playerInventory ->
            QuarryScreen.Handler(syncId, SimpleSidedInventory(io.size), playerInventory, ArrayPropertyDelegate(properties))
        }.also {
            HandledScreens.register(it) { handler, playerInventory, title ->
                QuarryScreen(this, handler, playerInventory, title)
            }
        }

    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: QuarryBlock.Entity) {
        if (blockEntity.getStack(1).count == blockEntity.getStack(1).maxCount) {
            blockEntity.setPropertyState(state.with(Properties.LIT, false))
            blockEntity.progress = 0
            return
        }
        blockEntity.setPropertyState(state.with(Properties.LIT, true))
        blockEntity.progress++
        if (blockEntity.progress >= blockEntity.duration) {
            blockEntity.progress = 0
            blockEntity.insert(ores(world.random).apply { count = blockEntity.fortune })
            blockEntity.efficiency = 1 + blockEntity.getStack(0).getEnchantmentLevel(Enchantments.EFFICIENCY)
            blockEntity.duration = Minecraft.TICKS / blockEntity.efficiency
            blockEntity.fortune = 1 + blockEntity.getStack(0).getEnchantmentLevel(Enchantments.FORTUNE)
        }
    }

    private fun ores(random: Random): ItemStack {
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
                return ItemStack(ore.first, 1)
            }
        }
        return ItemStack.EMPTY
    }

}
