package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.AwesomeEnchantments
import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.Minecraft
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

    override fun onTargetDamaged(user: LivingEntity, target: Entity, level: Int) {
        if (user.world.isClient) return
        if (user is PlayerEntity && !user.world.isClient && !user.isSneaking) {
            (target as? LivingEntity)?.addStatusEffect(StatusEffectInstance(StatusEffects.SLOWNESS, Minecraft.TICKS, level))
        }
    }

    override fun canAccept(other: Enchantment): Boolean =
        listOf(
            AwesomeEnchantments.iceAspect,
            AwesomeEnchantments.poisonAspect,
            Enchantments.FIRE_ASPECT,
        ).contains(other).not()

}
