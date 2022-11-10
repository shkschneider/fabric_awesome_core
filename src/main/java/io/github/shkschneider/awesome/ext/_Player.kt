package io.github.shkschneider.awesome.ext

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.hit.HitResult

fun PlayerEntity.lookingAt(maxDistance: Double = 8.toDouble(), includeFluids: Boolean = false): HitResult? =
    this.raycast(maxDistance, 0.toFloat(), includeFluids)
        .takeIf { it.type in listOf(HitResult.Type.BLOCK, HitResult.Type.ENTITY) }
