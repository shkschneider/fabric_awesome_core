package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity

class UnbreakableEnchantment : AwesomeEnchantment(
    id = AwesomeUtils.identifier("unbreakable"),
    Rarity.RARE,
    levels = 1 to 1,
    EnchantmentTarget.BREAKABLE,
    listOf(EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND, EquipmentSlot.FEET, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.HEAD),
) {

    override fun invoke(livingEntity: LivingEntity, entity: Entity, level: Int) = Unit

    override fun canAccept(other: Enchantment): Boolean {
        return listOf(this, Enchantments.MENDING).contains(other).not()
    }

}
