package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.AwesomeEnchantments
import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.ext.id
import io.github.shkschneider.awesome.core.ext.isOre
import io.github.shkschneider.awesome.custom.Event
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.block.BlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.tag.BlockTags
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import kotlin.math.max
import kotlin.math.min

class VeinMiningEnchantment : AwesomeEnchantment(
    id = AwesomeUtils.identifier("vein_mining"),
    Rarity.RARE,
    levels = 1 to 5,
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
        if (!player.mainHandStack.isSuitableFor(state)) return
        if (isVeinMining) return
        if (player.isSneaking) return
        val veinMining = EnchantmentHelper.getLevel(AwesomeEnchantments.veinMining, player.mainHandStack)
        if (veinMining > 0) {
            if (state.isOre) {
                vein(world, pos, player, veinMining, state.block.id())
            } else if (state.isIn(BlockTags.LOGS) && state.isIn(BlockTags.AXE_MINEABLE)) {
                vein(world, pos, player, veinMining * 2, state.block.id())
            }
        }
    }

    private fun vein(world: World, blockPos: BlockPos, playerEntity: PlayerEntity, level: Int, type: Identifier) {
        isVeinMining = true
        val start = blockPos.mutableCopy().add(-level, -level, -level)
        val end = blockPos.mutableCopy().add(level, level, level)
        BlockPos.iterate(
            min(start.x, end.x), min(start.y, end.y), min(start.z, end.z),
            max(start.x, end.x), max(start.y, end.y), max(start.z, end.z),
        ).forEach { pos ->
            val state = world.getBlockState(pos)
            if (state.block.id() == type) {
                mine(world, state, pos, playerEntity)
            }
        }
        isVeinMining = false
    }

    private fun mine(world: World, state: BlockState, pos: BlockPos, player: PlayerEntity): Boolean {
        // PlayerBlockBreakEvents.BEFORE.invoker().beforeBlockBreak(world, player, pos, state, null)
        state.block.afterBreak(world, player, pos, state, world.getBlockEntity(pos), player.mainHandStack)
        world.removeBlock(pos, false)
        // PlayerBlockBreakEvents.AFTER.invoker().afterBlockBreak(world, player, pos, state, null)
        world.markDirty(pos)
        return true
    }

}
