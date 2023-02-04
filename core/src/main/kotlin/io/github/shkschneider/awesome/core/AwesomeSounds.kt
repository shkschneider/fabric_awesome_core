package io.github.shkschneider.awesome.core

import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object AwesomeSounds {

    val teleport = SoundEvents.ENTITY_ENDERMAN_TELEPORT to SoundCategory.PLAYERS
    val experience = SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP to SoundCategory.PLAYERS

    operator fun invoke(where: Pair<World, BlockPos>, sound: Pair<SoundEvent, SoundCategory>): Boolean {
        val world = where.first
        val pos = where.second
        if (world.isClient) return false
        world.playSound(null, pos, sound.first, sound.second, 1.toFloat(), 1.toFloat())
        return true
    }

}
