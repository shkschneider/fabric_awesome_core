package io.github.shkschneider.awesome.extras

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.MinecraftServer

object RecipesUnlocker {

    operator fun invoke(server: MinecraftServer, player: PlayerEntity) {
        val recipes = server.recipeManager.values()
        player.unlockRecipes(recipes)
    }

}
