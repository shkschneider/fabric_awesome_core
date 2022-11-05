package io.github.shkschneider.awesome.worldgen

import io.github.shkschneider.awesome.materials.AwesomeMaterials
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.block.Blocks
import net.minecraft.structure.rule.BlockMatchRuleTest
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.feature.ConfiguredFeatures
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreConfiguredFeatures
import net.minecraft.world.gen.feature.OreFeatureConfig
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier

object RandomiumWorldGen {

    const val ID = AwesomeMaterials.Randomium.ID

    operator fun invoke() {
        Overworld()
        End()
    }

    /**
     * Thanks to Kaupenjoe
     * Link: https://www.youtube.com/c/TKaupenjoe
     */
    object Overworld {

        private const val VEIN_SIZE = 7
        private const val VEINS_PER_CHUNK = 7
        private const val MIN = -48
        private const val MAX = 48

        operator fun invoke() {
            val config = OreFeatureConfig(listOf(
                OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, AwesomeMaterials.Randomium.ore.defaultState),
                OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, AwesomeMaterials.Randomium.deepslateOre.defaultState),
            ), VEIN_SIZE)
            val modifiers = listOf(
                CountPlacementModifier.of(VEINS_PER_CHUNK),
                HeightRangePlacementModifier.uniform(YOffset.fixed(MIN), YOffset.fixed(MAX)),
            )
            BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                PlacedFeatures.register("ore_${ID}_overworld", ConfiguredFeatures.register("ore_${ID}_overworld", Feature.ORE, config),
                modifiers
            ).key.get())
        }

    }

    /**
     * Thanks to Kaupenjoe
     * Link: https://www.youtube.com/c/TKaupenjoe
     */
    object End {

        private const val VEIN_SIZE = 9
        private const val VEINS_PER_CHUNK = 9
        private const val MIN = 32
        private const val MAX = 64

        operator fun invoke() {
            val config = OreFeatureConfig(listOf(
                OreFeatureConfig.createTarget(BlockMatchRuleTest(Blocks.END_STONE), AwesomeMaterials.Randomium.endOre.defaultState),
            ), VEIN_SIZE)
            val modifiers = listOf(
                CountPlacementModifier.of(VEINS_PER_CHUNK),
                HeightRangePlacementModifier.uniform(YOffset.fixed(MIN), YOffset.fixed(MAX)),
            )
            BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                PlacedFeatures.register("ore_${ID}_end", ConfiguredFeatures.register("ore_${ID}_end", Feature.ORE, config),
                    modifiers
                ).key.get())
        }


    }

}
