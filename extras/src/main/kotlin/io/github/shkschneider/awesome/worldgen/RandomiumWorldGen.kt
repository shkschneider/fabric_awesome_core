package io.github.shkschneider.awesome.worldgen

object RandomiumWorldGen {

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
            // OH MY 1.19 FIXME
//            val config = OreFeatureConfig(listOf(
//                OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, AwesomeBlocks.Randomium.ore.defaultState),
//                OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, AwesomeBlocks.Randomium.deepslateOre.defaultState),
//            ), VEIN_SIZE)
//            val modifiers = listOf(
//                CountPlacementModifier.of(VEINS_PER_CHUNK),
//                HeightRangePlacementModifier.uniform(YOffset.fixed(MIN), YOffset.fixed(MAX)),
//            )
//            BiomeModifications.addFeature(
//                BiomeSelectors.foundInOverworld(),
//                GenerationStep.Feature.UNDERGROUND_ORES,
//                PlacedFeatures.register("ore_${ID}_overworld", ConfiguredFeatures.register("ore_${ID}_overworld", Feature.ORE, config),
//                modifiers
//            ).key.get())
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
            // OH MY 1.19 FIXME
//            val config = OreFeatureConfig(listOf(
//                OreFeatureConfig.createTarget(BlockMatchRuleTest(Blocks.END_STONE), AwesomeBlocks.Randomium.endOre.defaultState),
//            ), VEIN_SIZE)
//            val modifiers = listOf(
//                CountPlacementModifier.of(VEINS_PER_CHUNK),
//                HeightRangePlacementModifier.uniform(YOffset.fixed(MIN), YOffset.fixed(MAX)),
//            )
//            BiomeModifications.addFeature(
//                BiomeSelectors.foundInTheEnd(),
//                GenerationStep.Feature.UNDERGROUND_ORES,
//                PlacedFeatures.register("ore_${ID}_end", ConfiguredFeatures.register("ore_${ID}_end", Feature.ORE, config),
//                    modifiers
//                ).key.get())
        }


    }

}
