package io.github.shkschneider.awesome.items

import com.google.common.base.Predicates
import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.core.AwesomeItem
import io.github.shkschneider.awesome.core.Minecraft
import io.github.shkschneider.awesome.core.ext.positions
import io.github.shkschneider.awesome.core.ext.toBox
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.OreBlock
import net.minecraft.block.RedstoneOreBlock
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.ShulkerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.Rarity
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.world.World
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Prospector : AwesomeItem(
    id = AwesomeUtils.identifier(ID),
    settings = FabricItemSettings().group(Awesome.GROUP).maxCount(1).rarity(Rarity.UNCOMMON),
) {

    companion object {

        const val ID = "prospector"

        val EXPERIENCE = 1
        val DURATION = AwesomeUtils.secondsToTicks(10)
        val COOLDOWN = if (Minecraft.isDevelopment) AwesomeUtils.secondsToTicks(1) else DURATION * 2
        val RANGE = Minecraft.CHUNK / 2 // ~4913 blocks

    }

    init {
        PlayerBlockBreakEvents.BEFORE.register(PlayerBlockBreakEvents.Before { world, _, pos, _, _ ->
            // world.getClosestEntity(ShulkerEntity::class.java, TargetPredicate.createNonAttackable(), null, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), pos.toBox(1.0))?.takeIf { it.scoreboardTags.contains(ID) }?.discard()
            // do not request only the closest as many entities might have spawn inside the same block during DURATION
            world.getEntitiesByClass(ShulkerEntity::class.java, pos.toBox(0.1), Predicates.alwaysTrue())
                .filter { it.scoreboardTags.contains(ID) }.forEach { it.discard() }
            return@Before true
        })
    }

    override fun hasGlint(stack: ItemStack): Boolean = true

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {}

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (world.isClient) return super.use(world, user, hand)
        if (user.isCreative.not() && user.experienceLevel < EXPERIENCE) return TypedActionResult.fail(user.mainHandStack)
        if (user.isCreative.not()) user.addExperienceLevels(-EXPERIENCE)
        // user.addStatusEffect(StatusEffectInstance(StatusEffects.NIGHT_VISION, DURATION))
        prospect(world as ServerWorld, user)
        user.itemCooldownManager.set(this, COOLDOWN)
        return TypedActionResult.success(user.mainHandStack)
    }

    private fun prospectorEntity(world: ServerWorld, pos: BlockPos): LivingEntity? =
        EntityType.SHULKER.spawn(world, NbtCompound(), null, null, pos.down(), SpawnReason.COMMAND, true, false)?.apply {
            addScoreboardTag(ID)
            clearGoalsAndTasks()
            isAiDisabled = true
            isGlowing = true
            isInvulnerable = true
            isSilent = true
            setNoDrag(true)
            setNoGravity(true)
            addStatusEffect(StatusEffectInstance(StatusEffects.INVISIBILITY, DURATION, 1, false, false))
        }

    private fun prospect(world: ServerWorld, player: PlayerEntity) {
        val box = Box(player.blockPos).expand(RANGE.toDouble())
        val positions = box.positions()
        positions.forEach { pos ->
            val state = world.getBlockState(pos)
            val block = state.block
            if (block is OreBlock || block is RedstoneOreBlock) {
                val entity = prospectorEntity(world, pos) ?: return@forEach
                // FIXME this might persist if disconnect etc.
                Executors.newSingleThreadScheduledExecutor().schedule(Runnable {
                    entity.discard()
                }, AwesomeUtils.ticksToSeconds(DURATION).toLong(), TimeUnit.SECONDS)
                world.spawnEntity(entity)
            }
        }
    }

}
