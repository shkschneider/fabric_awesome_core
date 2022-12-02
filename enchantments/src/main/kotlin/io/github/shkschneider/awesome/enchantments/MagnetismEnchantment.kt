package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.AwesomeEnchantments
import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.Magnetism
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.enchantment.EnchantmentHelper
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
        ServerTickEvents.END_SERVER_TICK.register(ServerTickEvents.EndTick { server ->
            server.playerManager.playerList.forEach { player ->
                val magnetism = EnchantmentHelper.getLevel(AwesomeEnchantments.magnetism, player.mainHandStack)
                if (player.isAlive && player.isSneaking.not() && magnetism > 0) {
                    Magnetism(player, magnetism)
                }
            }
        })
    }

    override fun invoke(livingEntity: LivingEntity, entity: Entity, level: Int) = Unit

}
