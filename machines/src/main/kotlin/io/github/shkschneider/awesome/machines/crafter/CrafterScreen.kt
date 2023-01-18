package io.github.shkschneider.awesome.machines.crafter

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.core.ext.getStacks
import io.github.shkschneider.awesome.machines.AwesomeMachineBlockScreen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.slot.Slot
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.text.Text
import kotlin.math.roundToInt

class CrafterScreen(
    name: String,
    handler: Handler,
    playerInventory: PlayerInventory,
    title: Text,
) : AwesomeMachineBlockScreen<CrafterScreen.Handler>(name, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        setShader()
        if (handler.progress > 0) {
            val progress = (handler.percent * 76.0).roundToInt()
            drawTexture(matrices, x + 64 - 1, y + 49 - 1, 176, 32, progress, 48 - 32)
        }
    }

    class Handler(
        syncId: Int,
        private val sidedInventory: SidedInventory,
        private val playerInventory: PlayerInventory,
        properties: PropertyDelegate,
    ) : AwesomeMachineBlockScreen.Handler(
        AwesomeMachines.crafter.screen, syncId, sidedInventory, playerInventory, properties
    ) {

        init {
            addProperties(properties)
            addSlots(
                // inputs
                80 to 17, 98 to 17, 116 to 17, 134 to 17, 152 to 17,
                // crafting grid
                8 to 17, 26 to 17, 44 to 17,
                8 to 35, 26 to 35, 44 to 35,
                8 to 52, 26 to 53, 44 to 52,
                // output
                144 + 4 to 45 + 4
            )
            addPlayerSlots()
        }

        override fun canInsertIntoSlot(stack: ItemStack, slot: Slot): Boolean =
            canInsertIntoSlot(slot) && (sidedInventory.getStacks().any { it.isEmpty || it.item == stack.item })

        override fun canInsertIntoSlot(slot: Slot): Boolean =
            Crafter.PORTS.isInput(slot.index) && (slot.index < Crafter.INVENTORY || slot.index >= Crafter.PORTS.size)

        override fun onSlotClick(slotIndex: Int, button: Int, actionType: SlotActionType, player: PlayerEntity) {
            if (slotIndex in (Crafter.INVENTORY until Crafter.PORTS.inputs.first)) {
                slots[slotIndex].stack = ItemStack(cursorStack.item, 1)
            } else {
                super.onSlotClick(slotIndex, button, actionType, player)
            }
        }

        override fun quickMove(player: PlayerEntity, i: Int): ItemStack =
            if (i in (0 until Crafter.INVENTORY) || i == Crafter.PORTS.size - 1) {
                super.quickMove(player, i)
            } else if (i >= Crafter.PORTS.size) {
                internalQuickMove(i)
            } else {
                ItemStack.EMPTY
            }

        private fun internalQuickMove(index: Int): ItemStack {
            val slot = slots.getOrNull(index)?.takeIf { it.hasStack() } ?: return ItemStack.EMPTY
            val stack = slot.stack.copy()
            if (!insertItem(slot.stack, 0, Crafter.INVENTORY, false)) {
                return ItemStack.EMPTY
            }
            slot.markDirty()
            return stack
        }

    }

}
