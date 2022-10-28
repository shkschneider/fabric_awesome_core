package io.github.shkschneider.awesome.mixins;

import net.minecraft.entity.passive.VillagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// https://github.com/ClusterFluxMC/extension
@Mixin(VillagerEntity.class)
public class VillagerEntityTickMixin {

    @Shadow
    private int restocksToday;

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo info) {
        restocksToday = 0;
        ((VillagerEntity) (Object) this).restock();
    }

}
