package io.github.shkschneider.awesome.custom

import io.github.shkschneider.awesome.AwesomeConfig
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

object Magnetism {

    operator fun invoke() = Unit

    init {
        if (AwesomeConfig.magnetismEnchantment) {
            PlayerBlockBreakEvents.AFTER.register(PlayerBlockBreakEvents.After { _: World, player: PlayerEntity, _: BlockPos, _: BlockState, _: BlockEntity? ->
                invoke(player)
            })
        }
    }

    operator fun invoke(player: PlayerEntity) {
        if (!AwesomeConfig.magnetismEnchantment) throw IllegalStateException()
        val magnetism = EnchantmentHelper.getLevel(AwesomeEnchantments.magnetism, player.mainHandStack)
        if (!player.isSneaking && magnetism > 0) {
            player.addStatusEffect(
                StatusEffectInstance(AwesomeEffects.magnetism, AwesomeUtils.TICK / 2 * magnetism, magnetism)
            )
        }
    }

}