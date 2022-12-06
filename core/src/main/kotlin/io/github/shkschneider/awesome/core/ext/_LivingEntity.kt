package io.github.shkschneider.awesome.core.ext

import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.Vec3d

fun LivingEntity.isBeingLookedAt(entity: LivingEntity): Boolean {
    val v1 = entity.getRotationVec(1.0F).normalize()
    val v2 = Vec3d(this.x - entity.x, this.eyeY - entity.eyeY, this.z - entity.z)
    val d = v2.length()
    val e = v1.dotProduct(v2.normalize())
    return if (e > 1.0 - 0.025 / d) entity.canSee(this) else false
}
