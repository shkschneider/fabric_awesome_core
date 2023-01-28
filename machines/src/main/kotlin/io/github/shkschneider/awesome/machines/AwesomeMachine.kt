package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.core.AwesomeBlockEntity
import io.github.shkschneider.awesome.custom.InputOutput
import io.github.shkschneider.awesome.custom.Minecraft
import net.minecraft.block.BlockState
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

abstract class AwesomeMachine<BE : AwesomeBlockEntity.WithInventory, SH : AwesomeMachineScreenHandler<BE>>(
    val id: String,
    val io: InputOutput,
    val properties: Int = PROPERTIES,
) {

    companion object {

        const val PROPERTIES = 3

    }

    private lateinit var _block: AwesomeMachineBlock<BE, SH>
    val block get() = _block

    private lateinit var _screen: ScreenHandlerType<SH>
    val screen get() = _screen

    init {
        init()
    }

    private fun init() {
        _block = block()
        if (Minecraft.isClient) _screen = screen()
    }

    abstract fun block(): AwesomeMachineBlock<BE, SH>

    abstract fun screen(): ScreenHandlerType<SH>

    abstract fun tick(world: World, pos: BlockPos, state: BlockState, blockEntity: BE)


}
