package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.Awesome;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Thanks Linkie & Shedaniel
 * Link: https://linkie.shedaniel.me/mappings
 */
@Environment(EnvType.CLIENT)
@Mixin(SplashTextResourceSupplier.class)
public class MainMenuTextMixin {

    @Inject(method = "get()Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
    public String get(CallbackInfoReturnable<String> info) {
        info.setReturnValue(info.getReturnValue());
        info.setReturnValue(String.format("%s %s!", Awesome.INSTANCE.getNAME(), SharedConstants.getGameVersion().getName()));
        return null;
    }

}
