package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.AwesomeUtils;
import io.github.shkschneider.awesome.effects.AwesomeEffects;
import io.github.shkschneider.awesome.enchantments.AwesomeEnchantments;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.List;

@Mixin(Block.class)
public class BlockGetDroppedStacksMixing {

    private static Boolean isVeinMining = false;

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;", at = @At("RETURN"), cancellable = true)
    private static void getDroppedStacks(BlockState state, ServerWorld world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack, @NotNull CallbackInfoReturnable<List<ItemStack>> cir) {
        cir.setReturnValue(cir.getReturnValue());
        if (entity instanceof PlayerEntity playerEntity) {
            if (state.getBlock() instanceof OreBlock) {
                final int experience = EnchantmentHelper.getLevel(AwesomeEnchantments.INSTANCE.getExperience(), playerEntity.getMainHandStack());
                if (experience > 0) {
                    playerEntity.addExperience(experience);
                }
            }
            final int veinMining = EnchantmentHelper.getLevel(AwesomeEnchantments.INSTANCE.getVeinMining(), playerEntity.getMainHandStack());
            if (!isVeinMining && !playerEntity.isSneaking() && veinMining > 0) {
                if (state.getBlock() instanceof OreBlock && state.isIn(BlockTags.PICKAXE_MINEABLE)) {
                    veinMining(world, pos, playerEntity, veinMining, state.getBlock().asItem());
                } else if (state.isIn(BlockTags.LOGS) && state.isIn(BlockTags.AXE_MINEABLE)) {
                    veinMining(world, pos, playerEntity, veinMining * 2, state.getBlock().asItem());
                }
            }
            final int magnetism = EnchantmentHelper.getLevel(AwesomeEnchantments.INSTANCE.getMagnetism(), playerEntity.getMainHandStack());
            if (!playerEntity.isSneaking() && magnetism > 0) {
                playerEntity.addStatusEffect(new StatusEffectInstance(AwesomeEffects.INSTANCE.getMagnetism(), (AwesomeUtils.TICK / 2) * magnetism, magnetism));
            }
        }
    }

    // TODO iterate while touching a similar item, not in a cube aread
    private static void veinMining(@NotNull World world, @NotNull BlockPos pos, @NotNull PlayerEntity playerEntity, int level, Item item) {
        isVeinMining = true;
        final BlockPos start = pos.mutableCopy().add(-level, -level, -level);
        final BlockPos end = pos.mutableCopy().add(level, level, level);
        final Iterable<BlockPos> candidates = BlockPos.iterate(Math.min(start.getX(), end.getX()), Math.min(start.getY(), end.getY()), Math.min(start.getZ(), end.getZ()), Math.max(start.getX(), end.getX()), Math.max(start.getY(), end.getY()), Math.max(start.getZ(), end.getZ()));
        for (BlockPos candidate : candidates) {
            if (candidate.asLong() == pos.asLong()) continue;
            final Block other = world.getBlockState(candidate).getBlock();
            if (other.asItem() == item) {
                other.afterBreak(world, playerEntity, candidate, world.getBlockState(candidate), world.getBlockEntity(candidate), ItemStack.EMPTY);
                world.removeBlock(candidate, false);
                world.markDirty(candidate);
            }
        }
        isVeinMining = false;
    }

}
