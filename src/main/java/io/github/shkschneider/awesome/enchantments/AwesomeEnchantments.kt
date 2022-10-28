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

object AwesomeEnchantments {

    val iceAspect = AwesomeEnchantment("ice_aspect", Enchantments.FIRE_ASPECT.rarity, 1 to Enchantments.FIRE_ASPECT.maxLevel, EnchantmentTarget.WEAPON, listOf(EquipmentSlot.MAINHAND)) { livingEntity: LivingEntity, target: Entity, level: Int ->
        if (livingEntity is PlayerEntity && !livingEntity.world.isClient && !livingEntity.isSneaking) {
            (target as? LivingEntity)?.addStatusEffect(StatusEffectInstance(StatusEffects.SLOWNESS, AwesomeUtils.TICK, level))
        }
    }

    val poisonAspect = AwesomeEnchantment("poison_aspect", Enchantments.FIRE_ASPECT.rarity, 1 to Enchantments.FIRE_ASPECT.maxLevel, EnchantmentTarget.WEAPON, listOf(EquipmentSlot.MAINHAND)) { livingEntity: LivingEntity, target: Entity, level: Int ->
        if (livingEntity is PlayerEntity && !livingEntity.world.isClient && !livingEntity.isSneaking) {
            (target as? LivingEntity)?.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, AwesomeUtils.TICK, level))
        }
    }

    val magnetism = AwesomeEnchantment("magnetism", Enchantment.Rarity.RARE, 1 to 1, EnchantmentTarget.DIGGER, listOf(EquipmentSlot.MAINHAND)) { _, _, _ -> }

    val experience = AwesomeEnchantment("experience", Enchantment.Rarity.UNCOMMON, 1 to 1, EnchantmentTarget.DIGGER, listOf(EquipmentSlot.MAINHAND)) { _, _, _ -> }

    val veinMining = AwesomeEnchantment("vein_mining", Enchantment.Rarity.RARE, 1 to 3, EnchantmentTarget.DIGGER, listOf(EquipmentSlot.MAINHAND)) { _, _, _ -> }

    operator fun invoke() = Unit

}