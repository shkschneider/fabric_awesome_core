package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.Awesome;
import net.minecraft.block.BlockState;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Author: LordDeatHunter
 * License: MIT
 * Source: https://github.com/LordDeatHunter/SilkSpawners
 */
@Mixin(SpawnerBlock.class)
public class SpawnerBlockOnStacksDroppedMixin {

    @Inject(method = "onStacksDropped", at = @At("HEAD"), cancellable = true)
    public void cancelXP(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, CallbackInfo ci) {
        if (Awesome.INSTANCE.getCONFIG().getEnchantments().getSilkTouchSpawners()) {
            ci.cancel();
        }
    }

}
