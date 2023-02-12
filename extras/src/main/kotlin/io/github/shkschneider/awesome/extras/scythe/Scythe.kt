package io.github.shkschneider.awesome.extras.scythe

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.core.AwesomeSounds
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.PlantBlock
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.MiningToolItem
import net.minecraft.item.ToolMaterials
import net.minecraft.item.Vanishable
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.ActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.event.GameEvent

class Scythe : MiningToolItem(
    /* attackDamage */ 0F,
    /* attackSpeed */ -3F,
    ToolMaterials.WOOD,
    BlockTags.HOE_MINEABLE,
    FabricItemSettings().maxDamage(ToolMaterials.WOOD.durability),
), Vanishable {

    init {
        AwesomeRegistries.item(AwesomeUtils.identifier("scythe"), this, Awesome.GROUP)
    }

    private fun check(block: Block): Boolean =
        block is PlantBlock

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        if (context.world.isClient) return ActionResult.PASS
        val efficiency = EnchantmentHelper.getLevel(Enchantments.EFFICIENCY, context.player?.mainHandStack)
        val range = 2 + efficiency
        val yOffset = if (check(context.world.getBlockState(context.blockPos).block)) 0 else 1
        var used = false
        (context.blockPos.x - range until context.blockPos.x + range).forEach { x ->
            (context.blockPos.z - range until context.blockPos.z + range).forEach { z ->
                val pos = BlockPos(x, context.blockPos.y + yOffset, z)
                val state = context.world.getBlockState(pos)
                if (check(state.block)) {
                    mow(context, pos)
                    used = true
                }
            }
        }
        return if (used) {
            context.stack.damage(efficiency, context.player) { player -> player?.sendToolBreakStatus(context.hand) }
            ActionResult.SUCCESS
        } else {
            ActionResult.PASS
        }
    }

    private fun mow(context: ItemUsageContext, pos: BlockPos) {
        val state = Blocks.AIR.defaultState
        context.world.setBlockState(pos, state)
        context.world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(context.player, state))
        AwesomeSounds(context.world to pos, AwesomeSounds.crop)
    }

}
