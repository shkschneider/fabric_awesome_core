package io.github.shkschneider.awesome.core

import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.*
import net.minecraft.world.World

abstract class AwesomeItem(
    val id: Identifier,
    settings: Settings,
) : Item(settings) {

    init {
        init()
    }

    private fun init() {
        AwesomeRegistries.item(id, this as Item)
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        super.appendTooltip(stack, world, tooltip, context)
        appendShiftableTooltip()?.let { text ->
            if (AwesomeInputs.shift()) {
                tooltip.add(text)
            } else {
                tooltip.add(TranslatableText(AwesomeUtils.translatable("item", "hint")).formatted(Formatting.GRAY))
            }
        }
    }

    open fun appendShiftableTooltip(): Text? = null

    override fun hasGlint(stack: ItemStack): Boolean =
        super.hasGlint(stack)

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> =
        super.use(world, user, hand)

    override fun useOnBlock(context: ItemUsageContext): ActionResult =
        super.useOnBlock(context)

    override fun useOnEntity(stack: ItemStack, user: PlayerEntity, entity: LivingEntity, hand: Hand): ActionResult =
        super.useOnEntity(stack, user, entity, hand)

}
