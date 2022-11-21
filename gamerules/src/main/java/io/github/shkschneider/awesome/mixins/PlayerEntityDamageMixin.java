package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.AwesomeGameRules;
import io.github.shkschneider.awesome.gamerules.PvpGameRule;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityDamageMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        //noinspection ConstantConditions
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (!player.world.getGameRules().getBoolean(AwesomeGameRules.INSTANCE.getPvp())) {
            PvpGameRule.INSTANCE.invoke(player, source, info);
        }
    }

}
