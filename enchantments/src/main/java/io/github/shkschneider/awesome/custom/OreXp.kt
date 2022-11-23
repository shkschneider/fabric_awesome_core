package io.github.shkschneider.awesome.custom

import io.github.shkschneider.awesome.AwesomeEnchantments
import io.github.shkschneider.awesome.core.AwesomeRegistries
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.block.BlockState
import net.minecraft.block.OreBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.GameRules
import net.minecraft.world.World

object OreXp {

    operator fun invoke() = AwesomeRegistries.gameRule("oreXp", GameRules.Category.PLAYER, true)

    init {
        PlayerBlockBreakEvents.AFTER.register(PlayerBlockBreakEvents.After { _: World, player: PlayerEntity, _: BlockPos, state: BlockState, _: BlockEntity? ->
            invoke(player, state)
        })
    }

    operator fun invoke(player: PlayerEntity, state: BlockState) {
        if (state.block is OreBlock) {
            val silkTouch = EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, player.mainHandStack)
            if (silkTouch > 0) return
            val experience = EnchantmentHelper.getLevel(AwesomeEnchantments.experience, player.mainHandStack)
            if (experience > 0) {
                player.addExperience(experience)
            }
        }
    }

}