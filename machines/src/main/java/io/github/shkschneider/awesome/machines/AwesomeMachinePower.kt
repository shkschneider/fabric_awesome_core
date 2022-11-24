package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.Minecraft
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.PlayerLookup
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.server.world.ServerWorld
import team.reborn.energy.api.EnergyStorage
import team.reborn.energy.api.base.SimpleEnergyStorage

class AwesomeMachinePower(
    private val blockEntity: AwesomeMachineBlockEntity,
) : SimpleEnergyStorage(Int.MAX_VALUE.toLong(), Minecraft.STACK.toLong(), Minecraft.STACK.toLong()) {

    companion object {

        val id = AwesomeUtils.identifier("power")
        val key = AwesomeUtils.key("power")

        operator fun invoke(type: BlockEntityType<out AwesomeMachineBlockEntity>) {
            EnergyStorage.SIDED.registerForBlockEntity({ blockEntity: AwesomeMachineBlockEntity, _ -> blockEntity.energy }, type)
            ClientPlayNetworking.registerGlobalReceiver(id) { client, _, packet, _ ->
                val power = packet.readLong()
                val pos = packet.readBlockPos()
                (client.world?.getBlockEntity(pos) as? AwesomeMachineBlockEntity)?.let { blockEntity ->
                    blockEntity.power = power
                }
            }
        }

    }

    override fun onFinalCommit() = with(blockEntity) {
        markDirty()
//        if (world?.isClient != false) return
        PlayerLookup.tracking((world as ServerWorld), pos).forEach { player ->
            ServerPlayNetworking.send(player, id, PacketByteBufs.create().apply {
                writeLong(power)
                writeBlockPos(pos)
            })
        }
    }

}

