package io.github.shkschneider.awesome.mixins;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Author: Parzivail-Modding-Team
 * License: LGPL3
 * Source: https://github.com/Parzivail-Modding-Team/HereBeNoDragons
 */
@Environment(EnvType.CLIENT)
@Mixin(LevelProperties.class)
public class LevelPropertiesGetLifecycleMixin {

    @Shadow
    @Final
    private Lifecycle lifecycle;

    @Inject(method = "getLifecycle()Lcom/mojang/serialization/Lifecycle;", at = @At("HEAD"), cancellable = true)
    private void getLifecycle(CallbackInfoReturnable<Lifecycle> info) {
        if (lifecycle == Lifecycle.experimental()) {
            info.setReturnValue(Lifecycle.stable());
            info.cancel();
        }
    }

}
