package io.github.shkschneider.awesome.loots

import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.condition.MatchToolLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.CopyNbtLootFunction
import net.minecraft.loot.provider.nbt.ContextLootNbtProvider
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.predicate.NumberRange
import net.minecraft.predicate.item.EnchantmentPredicate
import net.minecraft.predicate.item.ItemPredicate
import net.minecraft.util.Identifier

object AwesomeSpawnerLoot {

    operator fun invoke() {
        LootTableEvents.MODIFY.register { _, _, id, supplier, _ ->
            if (id == Identifier("blocks/spawner")) {
                supplier.pool(
                    LootPool.Builder()
                        .rolls(ConstantLootNumberProvider.create(1.toFloat()))
                        .with(ItemEntry.builder(Items.SPAWNER).build())
                        .apply(
                            CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                                .withOperation("SpawnData", "BlockEntityTag.SpawnData")
                                .withOperation("SpawnPotentials", "BlockEntityTag.SpawnPotentials")
                                .build()
                        )
                        .conditionally(
                            MatchToolLootCondition.builder(
                                ItemPredicate.Builder.create().enchantment(
                                    EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))
                                )
                            ).build()
                        ).build()
                )
            }
        }
    }

}