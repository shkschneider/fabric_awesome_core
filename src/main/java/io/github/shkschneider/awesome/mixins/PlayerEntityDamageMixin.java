package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.gamerules.AwesomeGameRules;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("UnusedMixin")
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityDamageMixin {

    @Shadow
    public float experienceProgress;

    @Shadow
    public int experienceLevel;

    @Shadow
    public int totalExperience;

    @Redirect(method = "getXpToDrop", at = @At(value = "FIELD", target = "Lnet/minecraft/world/GameRules;KEEP_INVENTORY:Lnet/minecraft/world/GameRules$Key;", opcode = Opcodes.GETSTATIC))
    private GameRules.Key<GameRules.BooleanRule> getXpToDrop() {
        return AwesomeGameRules.INSTANCE.keepXp();
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (AwesomeGameRules.INSTANCE.pvp(player.world)) {
            AwesomeGameRules.INSTANCE.pvp(player, source, info);
        }
    }

}
