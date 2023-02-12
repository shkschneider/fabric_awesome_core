package io.github.shkschneider.awesome.extras.crate

import io.github.shkschneider.awesome.core.AwesomeBlockEntity
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction

class CrateBlockEntity(
    pos: BlockPos, state: BlockState,
) : AwesomeBlockEntity.WithInventory(
    Crate.ID, Crate.block.entityType, pos, state, Crate.IO, 0 to 0,
), AwesomeBlockEntity.WithScreen {

    override fun getDisplayName(): Text =
        Text.translatable(AwesomeUtils.translatable("block", Crate.ID))

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ScreenHandler =
        CrateBlockScreenHandler(Crate.screen, syncId, playerInventory, this)

    override fun canInsert(slot: Int, stack: ItemStack, dir: Direction?): Boolean = true

    override fun canExtract(slot: Int, stack: ItemStack, dir: Direction?): Boolean = true

}
