package io.github.shkschneider.awesome.custom

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.Minecraft
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
        if (Awesome.CONFIG.magnetismEnchantment) {
            PlayerBlockBreakEvents.AFTER.register(PlayerBlockBreakEvents.After { _: World, player: PlayerEntity, _: BlockPos, _: BlockState, _: BlockEntity? ->
                invoke(player)
            })
        }
    }

    operator fun invoke(player: PlayerEntity) {
        if (!Awesome.CONFIG.magnetismEnchantment) throw IllegalStateException()
        val magnetism = EnchantmentHelper.getLevel(AwesomeEnchantments.magnetism, player.mainHandStack)
        if (!player.isSneaking && magnetism > 0) {
            player.addStatusEffect(
                StatusEffectInstance(AwesomeEffects.magnetism, Minecraft.TICK / 2 * magnetism, magnetism)
            )
        }
    }

}
