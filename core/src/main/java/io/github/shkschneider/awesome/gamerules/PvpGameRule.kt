package io.github.shkschneider.awesome.gamerules

import io.github.shkschneider.awesome.core.AwesomeRegistries
import net.minecraft.entity.Entity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.GameRules
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

object PvpGameRule {

    operator fun invoke() = AwesomeRegistries.gameRule("pvp", GameRules.Category.PLAYER, false)

    operator fun invoke(entity: Entity, source: DamageSource, info: CallbackInfoReturnable<Boolean>) {
        if ((entity is PlayerEntity) && source.source is PlayerEntity) {
            info.cancel()
        }
    }

}
