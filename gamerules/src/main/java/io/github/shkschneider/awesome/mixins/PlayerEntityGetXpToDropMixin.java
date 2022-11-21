package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.AwesomeGameRules;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityGetXpToDropMixin {

    @Shadow
    public float experienceProgress;

    @Shadow
    public int experienceLevel;

    @Shadow
    public int totalExperience;

    @Redirect(method = "getXpToDrop", at = @At(value = "FIELD", target = "Lnet/minecraft/world/GameRules;KEEP_INVENTORY:Lnet/minecraft/world/GameRules$Key;", opcode = Opcodes.GETSTATIC))
    private GameRules.Key<GameRules.BooleanRule> getXpToDrop() {
        return AwesomeGameRules.INSTANCE.getKeepXp();
    }

}
