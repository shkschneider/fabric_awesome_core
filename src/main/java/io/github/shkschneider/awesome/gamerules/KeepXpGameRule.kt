package io.github.shkschneider.awesome.gamerules

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry
import net.minecraft.entity.Entity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.world.GameRules
import net.minecraft.world.World
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

object KeepXpGameRule {

    operator fun invoke() = GameRuleRegistry.register("keepXp", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true))

    operator fun invoke(world: World) = world.gameRules.getBoolean(AwesomeGameRules.keepXp)

    operator fun invoke(oldPlayer: ServerPlayerEntity, newPlayer: ServerPlayerEntity) {
        newPlayer.experienceLevel = oldPlayer.experienceLevel
        newPlayer.totalExperience = oldPlayer.totalExperience
        newPlayer.experienceProgress = oldPlayer.experienceProgress
    }

}