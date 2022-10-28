package io.github.shkschneider.awesome.effects

import com.google.common.base.Predicates
import io.github.shkschneider.awesome.effects.potions.AwesomePotions
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Vec3d

object AwesomeEffects {

    val paralysis = AwesomeEffect.Continuous("paralysis", StatusEffectCategory.HARMFUL) { livingEntity, _ ->
        if (livingEntity.isAlive) {
            livingEntity.teleport(livingEntity.x, livingEntity.y, livingEntity.z)
            livingEntity.velocity = Vec3d.ZERO
        }
    }

    val experience1 = AwesomeEffect.Instant("experience1", StatusEffectCategory.BENEFICIAL) { livingEntity, _ ->
        if (livingEntity is PlayerEntity && livingEntity.isAlive) {
            livingEntity.addExperienceLevels(1)
        }
    }
    val experience9 = AwesomeEffect.Instant("experience9", StatusEffectCategory.BENEFICIAL) { livingEntity, _ ->
        if (livingEntity is PlayerEntity && livingEntity.isAlive) {
            livingEntity.addExperienceLevels(9)
        }
    }

    val magnetism = AwesomeEffect.Continuous("magnetism", StatusEffectCategory.BENEFICIAL, 0x000000) { livingEntity, level ->
        if (livingEntity is PlayerEntity && livingEntity.isAlive) {
            livingEntity.world.getEntitiesByClass(ItemEntity::class.java, livingEntity.boundingBox.expand(level.toDouble().times(8)), Predicates.alwaysTrue())
                .stream().map { it as ItemEntity }.forEach { itemEntity ->
                    itemEntity.onPlayerCollision(livingEntity)
                }
        }
    }

    operator fun invoke() {
        AwesomePotions()
    }

}
