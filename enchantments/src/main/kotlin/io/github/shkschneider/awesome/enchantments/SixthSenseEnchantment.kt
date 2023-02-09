package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.SixthSense
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity

class SixthSenseEnchantment : AwesomeEnchantment(
    id = AwesomeUtils.identifier("sixth_sense"),
    Enchantments.MENDING.rarity,
    levels = 1 to Enchantments.MENDING.maxLevel,
    EnchantmentTarget.ARMOR_HEAD,
    listOf(EquipmentSlot.HEAD),
) {

    override fun invoke(livingEntity: LivingEntity, entity: Entity, level: Int) {}

    override fun onUserDamaged(user: LivingEntity, attacker: Entity, level: Int) {
        SixthSense(user)
    }


}
