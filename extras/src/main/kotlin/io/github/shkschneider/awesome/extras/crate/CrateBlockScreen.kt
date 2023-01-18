package io.github.shkschneider.awesome.extras.crate

import io.github.shkschneider.awesome.core.AwesomeBlockScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text

class CrateBlockScreen(
    handler: CrateBlockScreenHandler, playerInventory: PlayerInventory, title: Text,
) : AwesomeBlockScreen<CrateBlockScreenHandler>(
    Crate.ID, handler, playerInventory, title,
)
