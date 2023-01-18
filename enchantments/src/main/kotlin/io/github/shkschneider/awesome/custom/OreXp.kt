package io.github.shkschneider.awesome.custom

import io.github.shkschneider.awesome.AwesomeEnchantments
import io.github.shkschneider.awesome.core.Event
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.block.BlockState
import net.minecraft.block.ExperienceDroppingBlock
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.player.PlayerEntity

object OreXp {

    operator fun invoke() {
        @Event("PlayerBlockBreakEvents.After")
        PlayerBlockBreakEvents.AFTER.register(PlayerBlockBreakEvents.After { _, player, _, state, _ ->
            invoke(player, state)
        })
    }

    private operator fun invoke(player: PlayerEntity, state: BlockState) {
        if (state.block !is ExperienceDroppingBlock) {
            val silkTouch = EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, player.mainHandStack)
            val experience = EnchantmentHelper.getLevel(AwesomeEnchantments.experience, player.mainHandStack)
            if (silkTouch == 0 && experience > 0) {
                player.addExperience(experience)
            }
        }
    }

}
