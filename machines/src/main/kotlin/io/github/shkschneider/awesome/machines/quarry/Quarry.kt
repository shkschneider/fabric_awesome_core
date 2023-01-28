package io.github.shkschneider.awesome.machines.quarry

import io.github.shkschneider.awesome.core.ext.getEnchantmentLevel
import io.github.shkschneider.awesome.custom.Faces
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.custom.Minecraft
import io.github.shkschneider.awesome.custom.SimpleSidedInventory
import io.github.shkschneider.awesome.machines.recycler.Recycler
import net.minecraft.block.BlockState
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.enchantment.Enchantments
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object Quarry {

    const val ID = "quarry"
    val IO = InputOutput(inputs = 1 to listOf(Faces.Top), outputs = 1 to listOf(Faces.Bottom))
    const val PROPERTIES = 4

    private lateinit var _screen: ScreenHandlerType<QuarryScreenHandler>
    val screen get() = _screen

    private lateinit var _block: QuarryBlock
    val block: QuarryBlock get() = _block

    operator fun invoke() {
        _block = QuarryBlock()
        if (Minecraft.isClient) {
            _screen = ScreenHandlerType { syncId, playerInventory ->
                QuarryScreenHandler(syncId, SimpleSidedInventory(Recycler.IO.size), playerInventory, ArrayPropertyDelegate(PROPERTIES))
            }
            HandledScreens.register(_screen) { handler, playerInventory, title ->
                QuarryScreen(ID, handler, playerInventory, title)
            }
        }
    }

    fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: QuarryBlockEntity) {
        if (world.isClient) return
        if (blockEntity.getStack(1).count == blockEntity.getStack(1).maxCount) {
            blockEntity.setPropertyState(state.with(Properties.LIT, false))
            blockEntity.progress = 0
            return
        }
        blockEntity.setPropertyState(state.with(Properties.LIT, true))
        blockEntity.progress++
        if (blockEntity.progress >= blockEntity.duration) {
            blockEntity.progress = 0
            blockEntity.insert(QuarryHelper.ore(world.random).apply { count = blockEntity.fortune })
            blockEntity.efficiency = 1 + blockEntity.getStack(0).getEnchantmentLevel(Enchantments.EFFICIENCY)
            blockEntity.fortune = 1 + blockEntity.getStack(0).getEnchantmentLevel(Enchantments.FORTUNE)
        }
    }

}
