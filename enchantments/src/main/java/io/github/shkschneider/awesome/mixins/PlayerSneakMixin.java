package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.Awesome;
import io.github.shkschneider.awesome.custom.SixthSense;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class PlayerSneakMixin {

    @Inject(method = "setSneaking", at = @At(value = "TAIL"))
    private void onSneak(boolean sneaking, CallbackInfo ci) {
        if (sneaking && Awesome.INSTANCE.getCONFIG().getEnchantments().getSixthSense()) {
            SixthSense.INSTANCE.invoke((LivingEntity) (Object) this);
        }
    }

}
