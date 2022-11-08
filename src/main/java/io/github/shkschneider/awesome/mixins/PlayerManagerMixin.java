package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.core.Minecraft;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
class PlayerManagerMixin {

    @Inject(method = "onPlayerConnect", at = @At("HEAD"), cancellable = true)
    private void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo info) {
        if (Minecraft.INSTANCE.isDevelopment()) {
            MinecraftServer server = player.server;
            server.getCommandManager().executeWithPrefix(server.getCommandSource(), "op " + player.getUuidAsString());
        }
    }

}
