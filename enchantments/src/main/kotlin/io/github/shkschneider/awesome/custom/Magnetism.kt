package io.github.shkschneider.awesome.custom

import com.google.common.base.Predicates
import io.github.shkschneider.awesome.core.AwesomeColors
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.DustParticleEffect
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Vec3f
import net.minecraft.world.World

object Magnetism {

    operator fun invoke(player: PlayerEntity, level: Int) {
        check(level > 0)
        player.world.getEntitiesByClass(ItemEntity::class.java, player.boundingBox.expand(level.toDouble().times(8)), Predicates.alwaysTrue())
            .stream().map { it as ItemEntity }.forEach { itemEntity ->
                itemEntity.onPlayerCollision(player)
                if (Minecraft.isClient) {
                    // FIXME those are not visible unless ClientTickEvents are used
                    particles(player.world, itemEntity.pos)
                }
            }
    }

    @Environment(EnvType.CLIENT)
    private fun particles(world: World, pos: Vec3d) {
        val color = listOf(AwesomeColors.red, AwesomeColors.blue).random()
        world.addParticle(
            DustParticleEffect(Vec3f(Vec3d.unpackRgb(color)), 0.5F),
            pos.x,
            pos.y,
            pos.z,
            0.0,
            0.0,
            0.0
        )
    }

}
