package io.github.shkschneider.awesome.events

import io.github.shkschneider.awesome.enchantments.AwesomeEnchantments.experience
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.block.BlockState
import net.minecraft.block.OreBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class OreExperience : PlayerBlockBreakEvents.After {

    override fun afterBlockBreak(world: World, player: PlayerEntity, pos: BlockPos, state: BlockState, blockEntity: BlockEntity?) {
        if (state.block is OreBlock) {
            val silkTouch = EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, player.mainHandStack)
            if (silkTouch > 0) return
            val experience = EnchantmentHelper.getLevel(experience, player.mainHandStack)
            if (experience > 0) {
                player.addExperience(experience)
            }
        }
    }

}