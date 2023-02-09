package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.VeinMining
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot

class VeinMiningEnchantment : AwesomeEnchantment(
    id = AwesomeUtils.identifier("vein_mining"),
    Rarity.RARE,
    levels = 1 to 3,
    EnchantmentTarget.DIGGER,
    listOf(EquipmentSlot.MAINHAND),
) {

    init {
        VeinMining()
    }

}
