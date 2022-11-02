package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.Awesome;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Thanks Linkie & Shedaniel
 * Link: https://linkie.shedaniel.me/mappings
 */
@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {

    @Inject(method = "get()Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
    public String get(CallbackInfoReturnable<String> info) {
        info.setReturnValue(Awesome.Companion.getNAME() + "!");
        return null;
    }

}
