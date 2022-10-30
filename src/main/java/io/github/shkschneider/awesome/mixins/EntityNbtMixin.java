package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.Awesome;
import io.github.shkschneider.awesome.entities.IEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Thanks to Kaupenjoe
 * Link: https://www.youtube.com/c/TKaupenjoe
 */
@Mixin(Entity.class)
public abstract class EntityNbtMixin implements IEntityData {

    private NbtCompound data;

    public NbtCompound getData() {
        if (data == null) {
            data = new NbtCompound();
        }
        return data;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void writeNbt(NbtCompound nbt, CallbackInfoReturnable info) {
        if (data != null) {
            nbt.put(Awesome.Companion.getID(), data);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void readNbt(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains(Awesome.Companion.getID(), 10)) {
            data = nbt.getCompound(Awesome.Companion.getID());
        }
    }

}
