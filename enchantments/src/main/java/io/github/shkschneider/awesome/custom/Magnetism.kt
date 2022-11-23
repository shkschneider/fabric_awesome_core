package io.github.shkschneider.awesome.custom

import io.github.shkschneider.awesome.AwesomeEnchantments
import io.github.shkschneider.awesome.effects.AwesomeEffects
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity

object Magnetism {

    operator fun invoke(player: PlayerEntity) {
        val magnetism = EnchantmentHelper.getLevel(AwesomeEnchantments.magnetism, player.mainHandStack)
        if (!player.isSneaking && magnetism > 0) {
            player.removeStatusEffect(AwesomeEffects.magnetism)
            player.addStatusEffect(
                StatusEffectInstance(AwesomeEffects.magnetism, Minecraft.TICKS / 2 * magnetism, magnetism)
            )
        }
    }

}
