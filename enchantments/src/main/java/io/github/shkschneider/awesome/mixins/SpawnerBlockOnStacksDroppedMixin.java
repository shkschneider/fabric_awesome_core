package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.Awesome;
import net.minecraft.block.BlockState;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Author: LordDeatHunter
 * License: MIT
 * Source: https://github.com/LordDeatHunter/SilkSpawners
 */
@Mixin(SpawnerBlock.class)
public class SpawnerBlockOnStacksDroppedMixin {

    @ModifyVariable(method = "onStacksDropped", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockWithEntity;onStacksDropped(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;Z)V", shift = At.Shift.BEFORE), argsOnly = true)
    public boolean onStacksDropped(boolean dropExperience, BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, boolean _dropExperience) {
        if (Awesome.INSTANCE.getCONFIG().getEnchantments().getSilkTouchSpawners()) {
            return !EnchantmentHelper.get(stack).containsKey(Enchantments.SILK_TOUCH);
        } else {
            return dropExperience;
        }
    }

}
