package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.core.AwesomeEnchantment
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity

class ExperienceEnchantment : AwesomeEnchantment(
    id = AwesomeUtils.identifier("experience"),
    Rarity.UNCOMMON,
    levels = 1 to 1,
    EnchantmentTarget.DIGGER,
    listOf(EquipmentSlot.MAINHAND),
) {

    override fun invoke(livingEntity: LivingEntity, entity: Entity, level: Int) = Unit

    override fun canAccept(other: Enchantment): Boolean {
        return listOf(this, Enchantments.SILK_TOUCH).contains(other).not()
    }

}
