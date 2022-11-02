package io.github.shkschneider.awesome.gamerules

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry
import net.minecraft.entity.Entity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.GameRules
import net.minecraft.world.World
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

object PvpGameRule {

    operator fun invoke() = GameRuleRegistry.register("pvp", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false))

    operator fun invoke(world: World) = world.gameRules.getBoolean(AwesomeGameRules.pvp)

    operator fun invoke(entity: Entity, source: DamageSource, info: CallbackInfoReturnable<Boolean>) {
        if ((entity is PlayerEntity) && source.source is PlayerEntity) {
            info.cancel()
        }
    }

}
