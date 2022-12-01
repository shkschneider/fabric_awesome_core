package io.github.shkschneider.awesome.effects

import com.google.common.base.Predicates
import io.github.shkschneider.awesome.AwesomeEnchantments
import io.github.shkschneider.awesome.core.AwesomeColors
import io.github.shkschneider.awesome.core.AwesomeEffect
import io.github.shkschneider.awesome.custom.Minecraft
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class MagnetismEffect : AwesomeEffect.Continuous("magnetism", StatusEffectCategory.BENEFICIAL, color = AwesomeColors.black) {

    init {
        PlayerBlockBreakEvents.AFTER.register(PlayerBlockBreakEvents.After { _: World, player: PlayerEntity, _: BlockPos, _: BlockState, _: BlockEntity? ->
            val magnetism = EnchantmentHelper.getLevel(AwesomeEnchantments.magnetism, player.mainHandStack)
            if (!player.isSneaking && magnetism > 0) {
                player.addStatusEffect(
                    StatusEffectInstance(AwesomeEffects.magnetism, Minecraft.TICKS / 2 * magnetism, magnetism)
                )
            }
        })
    }

    override fun invoke(entity: LivingEntity, amplifier: Int) {
        if (entity is PlayerEntity && entity.isAlive) {
            entity.world.getEntitiesByClass(ItemEntity::class.java, entity.boundingBox.expand(amplifier.toDouble().times(8)), Predicates.alwaysTrue())
                .stream().map { it as ItemEntity }.forEach { itemEntity ->
                    itemEntity.onPlayerCollision(entity)
                }
        }
    }

}
