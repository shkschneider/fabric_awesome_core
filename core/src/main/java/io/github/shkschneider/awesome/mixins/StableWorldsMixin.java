package io.github.shkschneider.awesome.mixins;

import com.mojang.serialization.Lifecycle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.server.integrated.IntegratedServerLoader;
import net.minecraft.world.SaveProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Author: rdvdev2
 * License: LGPL3
 * Source: https://github.com/rdvdev2/DisableCustomWorldsAdvice
 */
@Environment(EnvType.CLIENT)
@Mixin(IntegratedServerLoader.class)
public abstract class StableWorldsMixin {

    // The target method only calls SaveProperties::getLifecycle() to determine if it should show a backup screen. We
    // can redirect this method to never return Lifecycle::experimental(), disabling the screen for custom worlds, but
    // keeping it for legacy ones.
    @Redirect(
        method = "start(Lnet/minecraft/client/gui/screen/Screen;Ljava/lang/String;ZZ)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/SaveProperties;getLifecycle()Lcom/mojang/serialization/Lifecycle;"),
        require = 1
    )
    private Lifecycle onLoad(SaveProperties properties) {
        final Lifecycle lifecycle = properties.getLifecycle();
        if (lifecycle == Lifecycle.experimental()) {
            return Lifecycle.stable();
        }
        return lifecycle;
    }

    // The target method contains two calls to MinecraftClient::setScreen() that show the backup screen. The first one
    // (the one we're targeting) only gets called when we have a custom world and not a legacy one. As this call is at
    // the end of the method, we can safely inject a call to start the server without showing the screen and return.
    @Inject(
        method = "tryLoad",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V", ordinal = 0),
        cancellable = true
    )
    private static void onCreate(MinecraftClient client, CreateWorldScreen parent, Lifecycle lifecycle, Runnable loader, CallbackInfo ci) {
        loader.run();
        ci.cancel();
    }

}
