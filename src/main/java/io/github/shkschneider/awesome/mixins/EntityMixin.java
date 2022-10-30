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

@SuppressWarnings("UnusedMixin")
@Mixin(Entity.class)
public abstract class EntityMixin implements IEntityData {

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
            nbt.put(Awesome.ID, data);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void readNbt(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains(Awesome.ID, 10)) {
            data = nbt.getCompound(Awesome.ID);
        }
    }

}
