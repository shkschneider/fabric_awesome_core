package io.github.shkschneider.awesome.core

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.custom.Permissions
import io.github.shkschneider.awesome.mixins.BrewingRecipeRegistryMixin
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.potion.Potion
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.GameRules

object AwesomeRegistries {

    fun block/*WithItem*/(id: Identifier, block: Block, group: ItemGroup = Awesome.GROUP): Block =
        Registry.register(Registry.BLOCK, id, block).also {
            item(id, BlockItem(it, FabricItemSettings().group(group)))
        }

    fun blockEntityType(id: Identifier, block: Block, createBlockEntity: (BlockPos, BlockState) -> BlockEntity): BlockEntityType<BlockEntity> =
        Registry.register(Registry.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.create({ pos, state -> createBlockEntity(pos, state) }, block).build(null))

    fun command(name: String, permission: Permissions = Permissions.Anyone, run: (CommandContext<ServerCommandSource>) -> Int) {
        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher: CommandDispatcher<ServerCommandSource?>, _, _ ->
            dispatcher.register(CommandManager.literal(name).requires { it.hasPermissionLevel(permission.level) }.executes(run))
        })
    }

    fun enchantment(id: Identifier, enchantment: Enchantment): Enchantment =
        Registry.register(Registry.ENCHANTMENT, id, enchantment)

    fun gameRule/*Boolean*/(name: String, category: GameRules.Category, default: Boolean): GameRules.Key<GameRules.BooleanRule> =
        GameRuleRegistry.register(name, category, GameRuleFactory.createBooleanRule(default))

    fun item(id: Identifier, item: Item): Item =
        Registry.register(Registry.ITEM, id, item)

    fun potion(name: String, effectInstance: StatusEffectInstance, recipe: Pair<Potion, Item>?): Potion =
        Registry.register(Registry.POTION, name, Potion(effectInstance)).also {
            if (recipe != null) {
                BrewingRecipeRegistryMixin.registerPotionRecipe(recipe.first, recipe.second, it)
            }
        }

    fun statusEffect(name: String, statusEffect: StatusEffect): StatusEffect =
        Registry.register(Registry.STATUS_EFFECT, name, statusEffect)

}
