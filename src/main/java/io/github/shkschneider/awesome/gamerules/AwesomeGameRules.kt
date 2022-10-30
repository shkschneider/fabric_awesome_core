package io.github.shkschneider.awesome.gamerules

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry
import net.minecraft.entity.Entity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.GameRules
import net.minecraft.world.World
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

object AwesomeGameRules {

    private val SLEEPING_HEALS = GameRuleRegistry.register("sleepingHeals", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true))
    fun sleepingHeals(): GameRules.Key<GameRules.BooleanRule> = SLEEPING_HEALS
    fun sleepingHeals(world: World) = world.gameRules.getBoolean(SLEEPING_HEALS)

    private val PVP = GameRuleRegistry.register("pvp", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true))
    fun pvp(): GameRules.Key<GameRules.BooleanRule> = PVP
    fun pvp(world: World) = world.gameRules.getBoolean(PVP)
    fun pvp(entity: Entity, source: DamageSource, info: CallbackInfoReturnable<Boolean>) {
        if ((entity is PlayerEntity) && source.source is PlayerEntity) {
            info.cancel()
        }
    }

    private val KEEP_XP = GameRuleRegistry.register("keepXp", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true))
    fun keepXp(): GameRules.Key<GameRules.BooleanRule> = KEEP_XP
    fun keepXp(world: World) = world.gameRules.getBoolean(KEEP_XP)

    operator fun invoke() = Unit

}