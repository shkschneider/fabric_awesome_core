package io.github.shkschneider.awesome.extras.randomium

import io.github.shkschneider.awesome.core.AwesomeUtils
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.PlacedFeatures

object Randomium {

    const val ID = "randomium"

    val ore = RandomiumOre("${ID}_ore")
    val deepslateOre = RandomiumOre("deepslate_${ID}_ore")
    val endOre = RandomiumOre("end_${ID}_ore")

    operator fun invoke() {
        BiomeModifications.addFeature(
            BiomeSelectors.vanilla(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            PlacedFeatures.of(AwesomeUtils.identifier("randomium_ore").toString()),
        )
    }

}
