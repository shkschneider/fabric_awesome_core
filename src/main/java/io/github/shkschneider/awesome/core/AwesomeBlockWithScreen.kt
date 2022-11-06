package io.github.shkschneider.awesome.core

import com.mojang.blaze3d.systems.RenderSystem
import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.custom.ImplementedInventory
import io.github.shkschneider.awesome.custom.InputOutput
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.screen.slot.Slot
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.BlockMirror
import net.minecraft.util.BlockRotation
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

abstract class AwesomeBlockWithScreen(
    id: Identifier,
    settings: Settings,
    group: ItemGroup = Awesome.GROUP,
    override val slots: InputOutput.Slots,
) : AwesomeBlock.WithEntity(id, settings, group), IAwesomeBlockWithScreen {

    private lateinit var blockEntity: AwesomeBlockEntity.WithScreen

    private lateinit var screen: MachineScreen
    private lateinit var screenHandler: MachineScreenHandler
    private lateinit var screenHandlerType: ScreenHandlerType<MachineScreenHandler>

    override val delegate: PropertyDelegate get() = blockEntity.delegate
    override val inventory: ImplementedInventory get() = blockEntity
    override val state: BlockState get() = blockEntity.state

    init {
        if (Minecraft.isClient) {
            initScreenHandler()
        }
    }

    //region Block

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState =
        initState(defaultState)

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) =
        super.appendProperties(builder)

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        if (state.properties.none { it == Properties.FACING }) return state
        return state.with(Properties.FACING, rotation.rotate(state.get(Properties.FACING)))
    }

    override fun mirror(state: BlockState, mirror: BlockMirror): BlockState {
        if (state.properties.none { it == Properties.FACING }) return state
        return state.rotate(mirror.getRotation(state.get(Properties.FACING)))
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): AwesomeBlockEntity {
        blockEntity = MachineBlockEntity(this, pos, state, slots)
        return blockEntity
    }

    override fun createScreenHandlerFactory(state: BlockState, world: World, pos: BlockPos): NamedScreenHandlerFactory =
        blockEntity

    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hit: BlockHitResult): ActionResult {
        if (world.isClient) return super.onUse(state, world, pos, player, hand, hit)
        val screenHandlerFactory = state.createScreenHandlerFactory(world, pos)
        if (screenHandlerFactory != null) {
            player.openHandledScreen(screenHandlerFactory)
        }
        return ActionResult.SUCCESS
    }

    //endregion

    //region BlockEntity

    class MachineBlockEntity(
        private val parent: AwesomeBlockWithScreen,
        pos: BlockPos,
        state: BlockState,
        slots: InputOutput.Slots,
    ) : AwesomeBlockEntity.WithScreen(parent.entityType, pos, state, slots) {

        override val properties: Map<Int, Int> = parent.properties

        override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity?): MachineScreenHandler {
            val inventories = InputOutput.Inventories(internal = SimpleInventory(slots.size), player = playerInventory)
            parent.screenHandler = MachineScreenHandler(parent, syncId, parent.slots, inventories)
            return parent.screenHandler
        }

        override fun getDisplayName(): Text = parent.getDisplayName()

    }

    //endregion

    //region Screen

    class MachineScreen(
        private val parent: AwesomeBlockWithScreen,
        handler: MachineScreenHandler,
        playerInventory: PlayerInventory,
        title: Text,
    ) : HandledScreen<MachineScreenHandler>(handler, playerInventory, title) {

        override fun init() {
            super.init()
            titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2
        }

        override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader)
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
            RenderSystem.setShaderTexture(0, parent.getTexture())
            val x = (width - backgroundWidth) / 2
            val y = (height - backgroundHeight) / 2
            drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight)
            parent.draw(matrices, delta, mouseX, mouseY)
        }

        override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
            renderBackground(matrices)
            super.render(matrices, mouseX, mouseY, delta)
            drawMouseoverTooltip(matrices, mouseX, mouseY)
            parent.render(matrices, mouseX, mouseY, delta)
        }

    }

    //endregion

    //region ScreenHandler

    private fun initScreenHandler() {
        check(Minecraft.isClient)
        screenHandlerType = ScreenHandlerType<MachineScreenHandler> { syncId, playerInventory ->
            blockEntity.createMenu(syncId, playerInventory, null) as MachineScreenHandler
        }
        HandledScreens.register(screenHandlerType) { handler, inventory, title ->
            screen = MachineScreen(this, handler, inventory, title)
            return@register screen
        }
    }

    class MachineScreenHandler(
        private val parent: AwesomeBlockWithScreen,
        syncId: Int,
        val slots: InputOutput.Slots,
        val inventories: InputOutput.Inventories,
    ) : ScreenHandler(parent.screenHandlerType, syncId) {

        init {
            addProperties(parent.delegate)
            inventories.internal.onOpen(inventories.player.player)
            with(parent.initSlots(inventories.internal)) {
                first.forEach(::addSlot)
                if (second) addPlayerSlots()
            }
        }

        // https://fabricmc.net/wiki/tutorial:containers
        private fun addPlayerSlots() {
            // inventory
            for (i in 0..2) {
                for (l in 0..8) {
                    addSlot(Slot(inventories.player, l + i * 9 + 9, 8 + l * 18, 84 + i * 18))
                }
            }
            // hotbar
            for (i in 0..8) {
                addSlot(Slot(inventories.player, i, 8 + i * 18, 142))
            }
        }

        override fun transferSlot(player: PlayerEntity, index: Int): ItemStack = parent.transferSlot(player, index)

        override fun canUse(player: PlayerEntity): Boolean = parent.canUse(player)

    }

    //endregion

}
