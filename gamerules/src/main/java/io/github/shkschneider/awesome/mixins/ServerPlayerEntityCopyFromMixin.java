package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.AwesomeGameRules;
import io.github.shkschneider.awesome.gamerules.KeepXpGameRule;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityCopyFromMixin extends PlayerEntityDamageMixin {

    @Inject(method = "copyFrom", at = @At("TAIL"))
    private void copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        if (oldPlayer.world.getGameRules().getBoolean(AwesomeGameRules.INSTANCE.getKeepXp())) {
            //noinspection ConstantConditions
            ServerPlayerEntity newPlayer = (ServerPlayerEntity) (Object) this;
            KeepXpGameRule.INSTANCE.invoke(oldPlayer, newPlayer);
        }
    }

}
