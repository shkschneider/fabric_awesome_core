package io.github.shkschneider.awesome.enchantments

import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity

class MagnetismEnchantment : AwesomeEnchantment(
    id = "magnetism",
    Rarity.RARE,
    levels = 1 to 1,
    EnchantmentTarget.DIGGER,
    listOf(EquipmentSlot.MAINHAND),
) {

    override fun invoke(livingEntity: LivingEntity, entity: Entity, level: Int) = Unit

}