package io.github.shkschneider.awesome.machines.quarry

import net.minecraft.block.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.math.random.Random

object QuarryHelper {

    fun ore(random: Random): ItemStack {
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
