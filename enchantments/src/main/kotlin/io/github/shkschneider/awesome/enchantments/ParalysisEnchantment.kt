package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Vec3d

class ParalysisEnchantment : AwesomeEnchantment(
    id = AwesomeUtils.identifier("paralysis"),
    Enchantments.MENDING.rarity,
    levels = 1 to 1,
    EnchantmentTarget.WEAPON,
    listOf(EquipmentSlot.MAINHAND),
) {

    override fun onTargetDamaged(user: LivingEntity, target: Entity, level: Int) {
        if (user.world.isClient) return
        if (user is PlayerEntity && !user.isSneaking) {
            (target as? LivingEntity)?.let { livingEntity ->
                livingEntity.teleport(livingEntity.x, livingEntity.y, livingEntity.z)
                livingEntity.velocity = Vec3d.ZERO
            }
        }
    }

}
