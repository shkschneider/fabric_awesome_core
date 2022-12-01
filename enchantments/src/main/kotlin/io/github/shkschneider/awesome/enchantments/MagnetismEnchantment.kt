package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.Magnetism
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity

class MagnetismEnchantment : AwesomeEnchantment(
    id = AwesomeUtils.identifier("magnetism"),
    Rarity.RARE,
    levels = 1 to 1,
    EnchantmentTarget.DIGGER,
    listOf(EquipmentSlot.MAINHAND),
) {

    init {
        PlayerBlockBreakEvents.AFTER.register(PlayerBlockBreakEvents.After { _, player, _, _, _ ->
            Magnetism(player)
        })
    }

    override fun invoke(livingEntity: LivingEntity, entity: Entity, level: Int) = Unit

}
