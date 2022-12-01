package io.github.shkschneider.awesome.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.SplashOverlay;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.IntSupplier;

/**
 * Author: A5b84
 * License: LGPL3
 * Source: https://github.com/A5b84/dark-loading-screen
 */
@Pseudo
@Environment(EnvType.CLIENT)
@Mixin(SplashOverlay.class)
public class SplashScreenBackgroundMixin {

    @Shadow
    private static @Final int MONOCHROME_BLACK;

    @Mutable
    @Shadow
    private static @Final IntSupplier BRAND_ARGB;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void background(CallbackInfo info) {
        BRAND_ARGB = () -> MONOCHROME_BLACK;
    }

}
