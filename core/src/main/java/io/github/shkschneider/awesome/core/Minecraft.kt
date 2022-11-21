package io.github.shkschneider.awesome.core

import net.fabricmc.api.EnvType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.SharedConstants
import net.minecraft.util.Identifier

object Minecraft {

    const val ID = "minecraft"
    val NAME = ID.lowercase().replaceFirstChar { it.uppercaseChar() }

    val isDevelopment = FabricLoader.getInstance().isDevelopmentEnvironment
    val isServer get() = FabricLoader.getInstance().environmentType == EnvType.SERVER
    val isClient get() = FabricLoader.getInstance().environmentType == EnvType.CLIENT

    const val CHUNK = SharedConstants.CHUNK_WIDTH
    const val HEIGHT = SharedConstants.DEFAULT_WORLD_HEIGHT
    const val PORT = SharedConstants.DEFAULT_PORT
    const val STACK = 64
    val TICKS = SharedConstants.TICKS_PER_SECOND
    val VERSION = SharedConstants.getGameVersion().name ?: SharedConstants.VERSION_NAME

    fun identifier(id: String) = Identifier("$NAME:$id")

}
