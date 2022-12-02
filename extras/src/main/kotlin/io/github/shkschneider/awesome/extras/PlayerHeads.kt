package io.github.shkschneider.awesome.extras

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.ItemScatterer

object PlayerHeads {

    operator fun invoke(player: PlayerEntity) {
        ItemScatterer.spawn(player.world, player.blockPos, SimpleInventory(head(player)))
    }

    private fun head(player: PlayerEntity) =
        ItemStack(Items.PLAYER_HEAD).apply {
            setCustomName(player.name)
            nbt = NbtCompound().apply {
                putString("SkullOwner", "shtark")
            }
        }

}
