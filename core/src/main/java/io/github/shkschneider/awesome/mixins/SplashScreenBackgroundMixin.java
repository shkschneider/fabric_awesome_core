package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.Awesome;
import io.github.shkschneider.awesome.core.AwesomeColors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.IntSupplier;

/**
 * Author: A5b84
 * License: LGPL3
 * Source: https://github.com/A5b84/dark-loading-screen
 */
@Environment(EnvType.CLIENT)
@Mixin(SplashOverlay.class)
public class SplashScreenBackgroundMixin {

    @Mutable
    @Shadow
    private static @Final IntSupplier BRAND_ARGB;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void background(CallbackInfo info) {
        if (Awesome.INSTANCE.getCONFIG().getDarkSplashScreen()) {
            BRAND_ARGB = () -> ColorHelper.Argb.getArgb(255, 0, 0, 0); // MONOCHROME_BLACK
        } else {
            BRAND_ARGB = AwesomeColors.INSTANCE::getMojang;
        }
    }

}
