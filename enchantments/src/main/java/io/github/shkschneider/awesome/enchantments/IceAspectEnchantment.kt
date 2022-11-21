package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.AwesomeEnchantments
import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.Minecraft
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity

class IceAspectEnchantment : AwesomeEnchantment(
    id = AwesomeUtils.identifier("ice_aspect"),
    Enchantments.FIRE_ASPECT.rarity,
    levels = 1 to Enchantments.FIRE_ASPECT.maxLevel,
    EnchantmentTarget.WEAPON,
    listOf(EquipmentSlot.MAINHAND),
) {

    override fun invoke(livingEntity: LivingEntity, entity: Entity, level: Int) {
        if (livingEntity is PlayerEntity && !livingEntity.world.isClient && !livingEntity.isSneaking) {
            (entity as? LivingEntity)?.addStatusEffect(StatusEffectInstance(StatusEffects.SLOWNESS, Minecraft.TICKS, level))
        }
    }

    override fun canAccept(other: Enchantment): Boolean {
        return listOf(this, Enchantments.FIRE_ASPECT, AwesomeEnchantments.iceAspect, AwesomeEnchantments.poisonAspect).contains(other).not()
    }

}
