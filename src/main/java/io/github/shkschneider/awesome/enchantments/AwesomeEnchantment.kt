package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.Awesome
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

abstract class AwesomeEnchantment(
    val id: String,
    rarity: Rarity,
    private val levels: Pair<Int, Int>,
    target: EnchantmentTarget,
    slots: List<EquipmentSlot>,
) : Enchantment(rarity, target, slots.toTypedArray()) {

    operator fun invoke() {
        Registry.register(Registry.ENCHANTMENT, Identifier(Awesome.ID, id), this)
    }

    abstract fun invoke(livingEntity: LivingEntity, entity: Entity, level: Int)

    override fun onTargetDamaged(user: LivingEntity, target: Entity, level: Int) {
        if (!user.world.isClient) {
            invoke(user, target, level)
        }
        super.onTargetDamaged(user, target, level)
    }

    override fun getMinLevel(): Int {
        return levels.first
    }

    override fun getMaxLevel(): Int {
        return levels.second
    }

}