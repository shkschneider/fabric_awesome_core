package io.github.shkschneider.awesome.core

import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.custom.ImplementedInventory
import io.github.shkschneider.awesome.custom.InputOutput
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

interface IAwesomeBlockWithScreen : BlockEntityTicker<AwesomeBlockWithScreen.MachineBlockEntity> {

    val id: Identifier
    val slots: InputOutput.Slots

    val delegate: PropertyDelegate
    val inventory: ImplementedInventory
    val properties: Map<Int, Int>
    val state: BlockState

    fun canUse(player: PlayerEntity): Boolean = true
    fun draw(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {}
    fun getDisplayName(): Text = Text.translatable(AwesomeUtils.translatable("block", id.path))
    fun getTexture(): Identifier = AwesomeUtils.identifier("textures/gui/${id.path}.png")
    fun initSlots(inventory: Inventory): Pair<List<Slot>, Boolean> = emptyList<Slot>() to false
    fun initState(state: BlockState): BlockState = state
    fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {}
    fun transferSlot(player: PlayerEntity, index: Int): ItemStack = ItemStack.EMPTY
    override fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: AwesomeBlockWithScreen.MachineBlockEntity) {}

}
