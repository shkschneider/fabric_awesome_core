package io.github.shkschneider.awesome.mixins;

import com.mojang.authlib.minecraft.UserApiService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import io.github.shkschneider.awesome.core.Minecraft;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MinecraftClient.class, priority = 1001)
public class MinecraftCreateUserApiServiceMixin {

    @Inject(method = "createUserApiService", at = @At(value = "HEAD"), cancellable = true)
    public void createUserApiService(YggdrasilAuthenticationService authService, RunArgs runArgs, CallbackInfoReturnable<UserApiService> cir) {
        if (Minecraft.INSTANCE.isDevelopment()) {
            cir.setReturnValue(UserApiService.OFFLINE);
        }
    }

}
