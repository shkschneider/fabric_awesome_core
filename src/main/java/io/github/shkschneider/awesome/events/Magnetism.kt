package io.github.shkschneider.awesome.events

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.effects.AwesomeEffects
import io.github.shkschneider.awesome.enchantments.AwesomeEnchantments
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class Magnetism : PlayerBlockBreakEvents.After {

    override fun afterBlockBreak(world: World, player: PlayerEntity, pos: BlockPos, state: BlockState, blockEntity: BlockEntity?) {
        val magnetism = EnchantmentHelper.getLevel(AwesomeEnchantments.magnetism, player.mainHandStack)
        if (!player.isSneaking && magnetism > 0) {
            player.addStatusEffect(
                StatusEffectInstance(AwesomeEffects.magnetism, AwesomeUtils.TICK / 2 * magnetism, magnetism)
            )
        }
    }

}