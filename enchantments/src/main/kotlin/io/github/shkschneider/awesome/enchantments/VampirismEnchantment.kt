package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.AwesomeEnchantments
import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity

class VampirismEnchantment : AwesomeEnchantment(
    id = AwesomeUtils.identifier("vampirism"),
    Enchantments.FORTUNE.rarity,
    levels = 1 to 5,
    EnchantmentTarget.WEAPON,
    listOf(EquipmentSlot.MAINHAND),
) {

    override fun onTargetDamaged(user: LivingEntity, target: Entity, level: Int) {
        if (user.world.isClient) return
        (user as? PlayerEntity)?.takeIf { it.isAlive }?.let { playerEntity ->
            (target as? LivingEntity)?.takeIf { it.isAlive }?.let { livingEntity ->
                leech(playerEntity, level, livingEntity)
            }
        }
    }

    override fun canAccept(other: Enchantment): Boolean =
        listOf(Enchantments.FIRE_ASPECT, AwesomeEnchantments.iceAspect, AwesomeEnchantments.poisonAspect).contains(other).not()

    private fun leech(playerEntity: PlayerEntity, level: Int, livingEntity: LivingEntity) {
        val life = livingEntity.health
        playerEntity.health += life * (0.01F * level)
    }

}
