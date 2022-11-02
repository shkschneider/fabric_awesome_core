package io.github.shkschneider.awesome.gamerules

import io.github.shkschneider.awesome.AwesomeConfig
import io.github.shkschneider.awesome.enchantments.AwesomeEnchantments
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry
import net.minecraft.block.BlockState
import net.minecraft.block.OreBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.GameRules
import net.minecraft.world.World

object OreXpGameRule {

    operator fun invoke() = GameRuleRegistry.register("oreXp", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true))

    operator fun invoke(world: World): Boolean = world.gameRules.getBoolean(AwesomeGameRules.oreXp)

    init {
        if (AwesomeConfig.oreDropXpWithExperienceEnchantment) {
            PlayerBlockBreakEvents.AFTER.register(PlayerBlockBreakEvents.After { world: World, player: PlayerEntity, _: BlockPos, state: BlockState, _: BlockEntity? ->
                if (invoke(world)) {
                    invoke(player, state)
                }
            })
        }
    }

    operator fun invoke(player: PlayerEntity, state: BlockState) {
        if (!invoke(player.world)) return
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
