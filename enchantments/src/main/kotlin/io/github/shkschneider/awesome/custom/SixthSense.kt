package io.github.shkschneider.awesome.custom

import com.google.common.base.Predicates
import io.github.shkschneider.awesome.core.ext.toBox
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import kotlin.math.sqrt

/**
 * Inspiration: https://github.com/Majrusz/MajruszsEnchantmentsMod
 */
object SixthSense {

    operator fun invoke(livingEntity: LivingEntity) {
        val player = livingEntity as? PlayerEntity ?: return
        val box = player.blockPos.toBox(radius = (Minecraft.CHUNK * sqrt(Minecraft.CHUNK.toDouble())))
        player.world.getEntitiesByClass(LivingEntity::class.java, box, Predicates.alwaysTrue()).forEach { entity ->
            if (entity.uuid != livingEntity.uuid) {
                entity.addStatusEffect(StatusEffectInstance(StatusEffects.GLOWING, Minecraft.TICKS, 1))
            }
        }
    }

}
