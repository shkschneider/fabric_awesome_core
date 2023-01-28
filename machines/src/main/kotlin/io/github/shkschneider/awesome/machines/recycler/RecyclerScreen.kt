package io.github.shkschneider.awesome.machines.recycler

import io.github.shkschneider.awesome.AwesomeMachines
import io.github.shkschneider.awesome.machines.AwesomeMachine
import io.github.shkschneider.awesome.machines.AwesomeMachineScreen
import io.github.shkschneider.awesome.machines.AwesomeMachineScreenHandler
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.screen.PropertyDelegate
import net.minecraft.text.Text

@Suppress("RemoveRedundantQualifierName")
class RecyclerScreen(
    machine: AwesomeMachine<RecyclerBlock.Entity, RecyclerScreen.Handler>,
    handler: RecyclerScreen.Handler,
    playerInventory: PlayerInventory,
    title: Text?,
) : AwesomeMachineScreen<RecyclerBlock.Entity, RecyclerScreen.Handler>(machine, handler, playerInventory, title) {

    override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrices, delta, mouseX, mouseY)
        if (handler.duration > 0) {
            val progress = handler.progress * 13 / handler.duration
            if (progress > 0) drawTexture(matrices, x + 80, y + 36 + 13 - progress - 1, 176, 13 - progress - 1, 190 - 176, progress + 1)
        }
    }

    class Handler : AwesomeMachineScreenHandler<RecyclerBlock.Entity> {

        constructor(syncId: Int, blockEntity: RecyclerBlock.Entity, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
            AwesomeMachines.recycler.screen, syncId, blockEntity, playerInventory, properties
        )
        constructor(syncId: Int, sidedInventory: SidedInventory, playerInventory: PlayerInventory, properties: PropertyDelegate) : super(
            AwesomeMachines.recycler.screen, syncId, sidedInventory, playerInventory, properties)

        init {
            addProperties(properties)
            addSlots(
                30 + 4 to 31 + 4,
                92 to 17, 110 to 17, 128 to 17,
                92 to 35, 110 to 35, 128 to 35,
                92 to 53, 110 to 53, 128 to 53,
            )
            addPlayerSlots()
        }

    }

}
