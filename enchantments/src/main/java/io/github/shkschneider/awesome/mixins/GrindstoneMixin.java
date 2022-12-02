package io.github.shkschneider.awesome.mixins;

import io.github.shkschneider.awesome.custom.Disenchant;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.GrindstoneScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Author: mschae23
 * Source: https://github.com/mschae23/grind-enchantments
 * License: MIT
 */
@Mixin(GrindstoneScreenHandler.class)
public abstract class GrindstoneMixin extends ScreenHandler {

    @Shadow
    @Final
    private ScreenHandlerContext context;

    @Shadow
    @Final
    public Inventory input;

    @Shadow
    @Final
    public Inventory result;

    protected GrindstoneMixin(ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    @Inject(method = "updateResult", at = @At("RETURN"), cancellable = true)
    private void onUpdateResult(CallbackInfo ci) {
        if (input.getStack(0).hasEnchantments() && input.getStack(1).getItem() == Items.BOOK) {
            result.setStack(0, Disenchant.INSTANCE.invoke(input.getStack(0), false));
            sendContentUpdates();
            context.run((world, pos) -> world.syncWorldEvent(WorldEvents.GRINDSTONE_USED, pos, 0));
            ci.cancel();
        }
    }

    @Mixin(targets = "net/minecraft/screen/GrindstoneScreenHandler$2")
    public static class Slot2canInsertMixin extends Slot {

        public Slot2canInsertMixin(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Inject(method = "canInsert", at = @At("RETURN"), cancellable = true)
        private void canInsert(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
            if (stack.getItem() == Items.BOOK) {
                cir.setReturnValue(true);
            }
        }

    }

    @Mixin(targets = "net/minecraft/screen/GrindstoneScreenHandler$3")
    public static class Slot3canInsertMixin extends Slot {

        public Slot3canInsertMixin(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Inject(method = "canInsert", at = @At("RETURN"), cancellable = true)
        private void canInsert(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
            if (stack.getItem() == Items.BOOK) {
                cir.setReturnValue(true);
            }
        }

    }

}
