package io.github.shkschneider.awesome.extras.tool

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeInputs
import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.core.ext.axe
import io.github.shkschneider.awesome.core.ext.hoe
import io.github.shkschneider.awesome.core.ext.name
import io.github.shkschneider.awesome.core.ext.pickaxe
import io.github.shkschneider.awesome.core.ext.shovel
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.MiningToolItem
import net.minecraft.item.ToolMaterial
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand

class AwesomeTool(
    private val material: ToolMaterial,
) : MiningToolItem(
    /* attackDamage */ 5F,
    /* attackSpeed */ -2.8F,
    material,
    BlockTags.PICKAXE_MINEABLE,
    FabricItemSettings(),
) {

    init {
        AwesomeRegistries.item(AwesomeUtils.identifier("${material.name}_tool"), this, Awesome.GROUP)
    }

    private fun tools(): List<MiningToolItem> =
        listOf(material.axe(), material.hoe(), material.pickaxe(), material.shovel()).let { tools ->
            if (AwesomeInputs.shift() || AwesomeInputs.control()) tools.reversed() else tools
        }

    override fun getMiningSpeedMultiplier(stack: ItemStack, state: BlockState): Float =
        if (isSuitableFor(stack, state)) miningSpeed else 1F

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        tools().forEach { tool ->
            when (tool.useOnBlock(context)) {
                ActionResult.SUCCESS, ActionResult.CONSUME -> return ActionResult.success(context.world.isClient)
                else -> {}
            }
        }
        return ActionResult.FAIL
    }

    override fun useOnEntity(stack: ItemStack, user: PlayerEntity, entity: LivingEntity, hand: Hand): ActionResult {
        tools().forEach { tool ->
            when (tool.useOnEntity(stack, user, entity, hand)) {
                ActionResult.SUCCESS, ActionResult.CONSUME -> return ActionResult.success(user.world.isClient)
                else -> {}
            }
        }
        return ActionResult.FAIL
    }

    override fun isSuitableFor(state: BlockState): Boolean =
        tools().any { it.isSuitableFor(state) }

    override fun isSuitableFor(stack: ItemStack, state: BlockState): Boolean =
        tools().any { it.isSuitableFor(stack, state) }

}
