package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.AwesomeUtils
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity

class PoisonAspectEnchantment : AwesomeEnchantment(
    id = "poison_aspect",
    Enchantments.FIRE_ASPECT.rarity,
    levels = 1 to Enchantments.FIRE_ASPECT.maxLevel,
    EnchantmentTarget.WEAPON,
    listOf(EquipmentSlot.MAINHAND),
) {

    override fun invoke(livingEntity: LivingEntity, entity: Entity, level: Int) {
        if (livingEntity is PlayerEntity && !livingEntity.world.isClient && !livingEntity.isSneaking) {
            (entity as? LivingEntity)?.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, AwesomeUtils.TICK, level))
        }
    }

    override fun canAccept(other: Enchantment): Boolean {
        return listOf(this, Enchantments.FIRE_ASPECT, AwesomeEnchantments.iceAspect, AwesomeEnchantments.poisonAspect).contains(other).not()
    }

}