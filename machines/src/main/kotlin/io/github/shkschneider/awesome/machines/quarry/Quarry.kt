package io.github.shkschneider.awesome.machines.quarry

import io.github.shkschneider.awesome.core.ext.getEnchantmentLevel
import io.github.shkschneider.awesome.custom.Faces
import io.github.shkschneider.awesome.custom.MachinePorts
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
    val PORTS = MachinePorts(
        1 to listOf(Faces.Top),
        1 to listOf(Faces.Bottom),
    )
    const val INPUT = 0
    const val OUTPUT = 1
    val PROPERTIES = 4

    private lateinit var SCREEN: ScreenHandlerType<QuarryScreenHandler>
    val screen get() = SCREEN

    private lateinit var BLOCK: QuarryBlock
    val block: QuarryBlock get() = BLOCK

    operator fun invoke() {
        BLOCK = QuarryBlock()
        if (Minecraft.isClient) {
            SCREEN = ScreenHandlerType { syncId, playerInventory ->
                QuarryScreenHandler(syncId, SimpleSidedInventory(Recycler.PORTS.size), playerInventory, ArrayPropertyDelegate(PROPERTIES))
            }
            HandledScreens.register(SCREEN) { handler, playerInventory, title ->
                QuarryScreen(ID, handler, playerInventory, title)
            }
        }
    }

    fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: QuarryBlockEntity) {
        if (blockEntity.getStack(OUTPUT).count == blockEntity.getStack(OUTPUT).maxCount) {
            blockEntity.setPropertyState(state.with(Properties.LIT, false))
            blockEntity.progress = 0
            return
        }
        blockEntity.setPropertyState(state.with(Properties.LIT, true))
        blockEntity.progress++
        if (blockEntity.progress >= blockEntity.duration) {
            blockEntity.progress = 0
            blockEntity.insert(QuarryHelper.ore(world.random).apply { count = blockEntity.fortune })
            blockEntity.efficiency = 1 + blockEntity.getStack(INPUT).getEnchantmentLevel(Enchantments.EFFICIENCY)
            blockEntity.fortune = 1 + blockEntity.getStack(INPUT).getEnchantmentLevel(Enchantments.FORTUNE)
        }
    }

}
