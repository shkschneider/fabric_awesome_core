package io.github.shkschneider.awesome.mixins;

import com.mojang.serialization.Lifecycle;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// https://github.com/Parzivail-Modding-Team/HereBeNoDragons
@Mixin(LevelProperties.class)
public class LevelPropertiesMixin {

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
