package io.github.shkschneider.awesome.effects

import com.google.common.base.Predicates
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity

class MagnetismEffect : AwesomeEffect.Continuous("magnetism", StatusEffectCategory.BENEFICIAL, color = 0x000000) {

    override fun invoke(entity: LivingEntity, amplifier: Int) {
        if (entity is PlayerEntity && entity.isAlive) {
            entity.world.getEntitiesByClass(ItemEntity::class.java, entity.boundingBox.expand(amplifier.toDouble().times(8)), Predicates.alwaysTrue())
                .stream().map { it as ItemEntity }.forEach { itemEntity ->
                    itemEntity.onPlayerCollision(entity)
                }
        }
    }

}