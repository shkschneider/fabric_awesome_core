package io.github.shkschneider.awesome.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Pseudo;
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
@Pseudo
@Environment(EnvType.CLIENT)
@Mixin(SplashOverlay.class)
public class SplashScreenBackgroundMixin {

    @Shadow
    private static @Final int MONOCHROME_BLACK;

    @Shadow
    private float progress;

    @Mutable
    @Shadow
    private static @Final IntSupplier BRAND_ARGB;

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void background(CallbackInfo info) {
        BRAND_ARGB = () -> MONOCHROME_BLACK;
    }

    @Inject(method = "renderProgressBar", at = @At("HEAD"), cancellable = true)
    private void renderProgressBar(MatrixStack matrices, int minX, int minY, int maxX, int maxY, float opacity, CallbackInfo ci) {
        int i = MathHelper.ceil((float)(maxX - minX - 2) * this.progress);
        int j = Math.round(opacity * 255.0F);
        int k = ColorHelper.Argb.getArgb(j, 255, 255, 255);
        DrawableHelper.fill(matrices, minX + 2, minY + 2, minX + i, maxY - 2, k);
        DrawableHelper.fill(matrices, minX + 1, minY, maxX - 1, minY + 1, k);
        DrawableHelper.fill(matrices, minX + 1, maxY, maxX - 1, maxY - 1, k);
        DrawableHelper.fill(matrices, minX, minY, minX + 1, maxY, k);
        DrawableHelper.fill(matrices, maxX, minY, maxX - 1, maxY, k);
        ci.cancel();
    }

}
