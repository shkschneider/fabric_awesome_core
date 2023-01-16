package io.github.shkschneider.awesome.extras.entities

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeColors
import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.core.AwesomeUtils
import io.github.shkschneider.awesome.custom.Minecraft
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.mob.MobEntity
import net.minecraft.item.ItemGroup
import net.minecraft.item.SpawnEggItem

object AwesomeEntities {

    operator fun invoke() {
        if (Awesome.CONFIG.extras.herobrine) {
            AwesomeRegistries.hostileEntity(
                Herobrine.ID,
                FabricEntityTypeBuilder.create(SpawnGroup.MONSTER) { type, world ->
                    Herobrine(type, world)
                }.dimensions(EntityDimensions.fixed(Herobrine.SIZE.first, Herobrine.SIZE.second))
            ).also { entityType ->
                if (Minecraft.isClient) {
                    FabricDefaultAttributeRegistry.register(entityType, Herobrine.attributes())
                    EntityRendererRegistry.register(entityType, ::HerobrineRenderer)
                }
                Herobrine.spawnRules(entityType)
                if (Awesome.CONFIG.extras.spawnEggs) {
                    spawnEgg(Herobrine.NAME, AwesomeColors.valencia to AwesomeColors.tuna, entityType)
                }
            }
        }
        if (Awesome.CONFIG.extras.spawnEggs) {
            spawnEgg("wither", AwesomeColors.black to AwesomeColors.white, EntityType.WITHER, ItemGroup.MISC)
            spawnEgg("dragon", AwesomeColors.black to AwesomeColors.valencia, EntityType.ENDER_DRAGON, ItemGroup.MISC)
        }
    }

    private fun spawnEgg(name: String, colors: Pair<Int, Int>, entityType: EntityType<out MobEntity>, group: ItemGroup = Awesome.GROUP): SpawnEggItem {
        val spawnEgg = SpawnEggItem(entityType, colors.first, colors.second, FabricItemSettings().group(group))
        AwesomeRegistries.item(AwesomeUtils.identifier("${name}_spawn_egg"), spawnEgg)
        return spawnEgg
    }

}
