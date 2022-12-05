package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.Awesome;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(PlayerScreenHandler.class)
public abstract class TrashScreenHandlerMixin extends ScreenHandler {

    private SimpleInventory trashInventory = null;

    public TrashScreenHandlerMixin(ScreenHandlerType<?> screenHandlerType, int i) {
        super(screenHandlerType, i);
    }

    @Inject(method = "<init>(Lnet/minecraft/entity/player/PlayerInventory;ZLnet/minecraft/entity/player/PlayerEntity;)V", at = @At(value = "TAIL"))
    private void init(PlayerInventory inventory, boolean onServer, final PlayerEntity owner, CallbackInfo ci) {
        if (Awesome.INSTANCE.getCONFIG().getExtras().getTrashSlot()) {
            trashInventory = new SimpleInventory(1);
            addSlot(new Slot(trashInventory, 0,152, 66));
        }
    }

    @Inject(method = "close", at = @At("TAIL"))
    public void close(PlayerEntity player, CallbackInfo ci) {
        if (trashInventory != null) {
            trashInventory.clear();
        }
    }

}
