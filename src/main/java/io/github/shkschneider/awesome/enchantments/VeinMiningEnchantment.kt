package io.github.shkschneider.awesome.enchantments

import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity

class VeinMiningEnchantment : AwesomeEnchantment(
    id = "vein_mining",
    Rarity.RARE,
    levels = 1 to 3,
    EnchantmentTarget.DIGGER,
    listOf(EquipmentSlot.MAINHAND),
) {

    override fun invoke(livingEntity: LivingEntity, entity: Entity, level: Int) = Unit

}