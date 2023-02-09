package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.AwesomeEnchantments
import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.Event
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.block.BlockState
import net.minecraft.block.ExperienceDroppingBlock
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import kotlin.math.max
import kotlin.math.min

class VeinMiningEnchantment : AwesomeEnchantment(
    id = AwesomeUtils.identifier("vein_mining"),
    Rarity.RARE,
    levels = 1 to 3,
    EnchantmentTarget.DIGGER,
    listOf(EquipmentSlot.MAINHAND),
) {

    private var isVeinMining = false

    init {
        @Event("PlayerBlockBreakEvents")
        PlayerBlockBreakEvents.AFTER.register(PlayerBlockBreakEvents.After { world, player, pos, state, _ ->
            invoke(world, player, pos, state)
        })
    }

    private operator fun invoke(world: World, player: PlayerEntity, pos: BlockPos, state: BlockState) {
        val veinMining = EnchantmentHelper.getLevel(AwesomeEnchantments.veinMining, player.mainHandStack)
        // FIXME check for tool
        if (!isVeinMining && !player.isSneaking && veinMining > 0) {
            if (state.block is ExperienceDroppingBlock && state.isIn(BlockTags.PICKAXE_MINEABLE)) {
                veinMining(world, pos, player, veinMining, state.block.asItem())
            } else if (state.isIn(BlockTags.LOGS) && state.isIn(BlockTags.AXE_MINEABLE)) {
                veinMining(world, pos, player, veinMining * 2, state.block.asItem())
            }
        }
    }

    // TODO iterate while touching a similar item, not in a cube around and limit to 64
    private fun veinMining(world: World, pos: BlockPos, playerEntity: PlayerEntity, level: Int, item: Item) {
        isVeinMining = true
        val start = pos.mutableCopy().add(-level, -level, -level)
        val end = pos.mutableCopy().add(level, level, level)
        val candidates = BlockPos.iterate(
            min(start.x, end.x), min(start.y, end.y), min(start.z, end.z),
            max(start.x, end.x), max(start.y, end.y), max(start.z, end.z)
        )
        for (candidate in candidates) {
            if (candidate.asLong() == pos.asLong()) continue
            val other = world.getBlockState(candidate).block
            if (other.asItem() === item) {
                other.afterBreak(world, playerEntity, candidate, world.getBlockState(candidate), world.getBlockEntity(candidate), ItemStack.EMPTY)
                world.removeBlock(candidate, false)
                world.markDirty(candidate)
            }
        }
        isVeinMining = false
    }

}
