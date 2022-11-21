package io.github.shkschneider.awesome.core

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text
import net.minecraft.text.Texts
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import net.minecraft.world.World
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object Dimensions {

    const val NETHER = -1
    const val OVERWORLD = 0
    const val END = 1
    const val UNKNOWN = Int.MIN_VALUE

    private var CURRENT: Identifier = World.OVERWORLD.value

    fun World.id(): Int {
        val identifier = dimensionKey.value
        return when (identifier.namespace) {
            "minecraft" -> when (identifier.path) {
                World.NETHER.value.namespace -> NETHER
                World.OVERWORLD.value.namespace -> OVERWORLD
                World.END.value.namespace -> END
                else -> throw IllegalStateException()
            }
            else -> UNKNOWN
        }
    }

    fun World.spawn() =
        Location(registryKey, levelProperties.spawnX.toDouble(), levelProperties.spawnY.toDouble(), levelProperties.spawnZ.toDouble())

    operator fun invoke() {
        if (Minecraft.isClient) {
            ClientPlayConnectionEvents.JOIN.register(ClientPlayConnectionEvents.Join { _, _, _ ->
                ClientChunkEvents.CHUNK_LOAD.register(ClientChunkEvents.Load { world, _ ->
                    val current = world.dimensionKey.value
                    if (current != CURRENT) {
                        CURRENT = world.dimensionKey.value
                        // delay because the client ticks while the world is still loading
                        Executors.newSingleThreadScheduledExecutor().schedule(Runnable {
                            MinecraftClient.getInstance().player?.let { player ->
                                AwesomeChat.overlay(player, Texts.join(listOf(
                                    Text.literal("${current.namespace}:".lowercase().replaceFirstChar { it.uppercaseChar() }).formatted(Formatting.ITALIC).formatted(Formatting.WHITE),
                                    Text.literal(current.path.uppercase()).formatted(Formatting.BOLD).formatted(Formatting.YELLOW),
                                ), Text.of(" ")))
                            }
                        }, AwesomeUtils.ticksToSeconds(Minecraft.TICKS).toLong(), TimeUnit.SECONDS)
                    }
                })
            })
        }
    }

}
