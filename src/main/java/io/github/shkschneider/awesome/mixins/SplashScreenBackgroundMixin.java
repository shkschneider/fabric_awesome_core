package io.github.shkschneider.awesome.mixins;

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

// https://github.com/A5b84/dark-loading-screen
@Mixin(SplashOverlay.class)
public class SplashScreenBackgroundMixin {

    @Mutable
    @Shadow
    private static @Final IntSupplier BRAND_ARGB;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void background(CallbackInfo info) {
        BRAND_ARGB = () -> ColorHelper.Argb.getArgb(255, 0, 0, 0); // MONOCHROME_BLACK
    }

}
