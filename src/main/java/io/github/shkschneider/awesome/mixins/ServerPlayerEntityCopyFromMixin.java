package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.gamerules.AwesomeGameRules;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("UnusedMixin")
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityCopyFromMixin extends PlayerEntityDamageMixin {

    @Inject(method = "copyFrom", at = @At("TAIL"))
    private void copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        if (AwesomeGameRules.INSTANCE.keepXp(oldPlayer.world)) {
            this.experienceLevel = oldPlayer.experienceLevel;
            this.totalExperience = oldPlayer.totalExperience;
            this.experienceProgress = oldPlayer.experienceProgress;
        }
    }

}
