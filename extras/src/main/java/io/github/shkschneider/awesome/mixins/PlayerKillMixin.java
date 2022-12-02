package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.Awesome;
import io.github.shkschneider.awesome.extras.PlayerHeads;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Author: ByMartrixx
 * License: MIT
 * Source: https://github.com/ByMartrixx/player-events/blob/master/api/src/main/java/me/bymartrixx/playerevents/api/mixin/ServerPlayerEntityMixin.java
 */
@Mixin(ServerPlayerEntity.class)
public class PlayerKillMixin {

    @Inject(method = "onDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;getPrimeAdversary()Lnet/minecraft/entity/LivingEntity;"))
    private void onDeath(DamageSource source, CallbackInfo ci) {
        if (Awesome.INSTANCE.getCONFIG().getExtras().getPlayerHeads()) {
            final ServerPlayerEntity killed = (ServerPlayerEntity) (Object) this;
            if (source.getAttacker() instanceof ServerPlayerEntity) {
                PlayerHeads.INSTANCE.invoke(killed);
            }
        }
    }

}
