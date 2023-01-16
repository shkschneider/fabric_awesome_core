package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.core.AwesomeUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * Author: LordDeatHunter
 * License: MIT
 * Source: https://github.com/LordDeatHunter/SilkSpawners
 */
@Mixin(Item.class)
public class ItemAppendTooltipMixin {

    @Inject(method = "appendTooltip", at = @At("HEAD"))
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        NbtCompound tag = stack.getNbt();
        if (stack.getItem() != Items.SPAWNER || tag == null) return;
        String spawnDataEntityId = tag.getCompound(AwesomeUtils.BLOCK_ENTITY_TAG)
                .getCompound("SpawnData")
                .getCompound("entity")
                .getString("id");
        if (!spawnDataEntityId.isBlank()) {
            tooltip.add(new LiteralText(spawnDataEntityId));
        }
    }

}
