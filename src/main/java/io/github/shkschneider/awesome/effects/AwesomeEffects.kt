package io.github.shkschneider.awesome.effects

import io.github.shkschneider.awesome.custom.Magnetism
import io.github.shkschneider.awesome.custom.VeinMining
import io.github.shkschneider.awesome.effects.potions.AwesomePotions

object AwesomeEffects {

    val experience1 = ExperienceEffect(levels = 1)
    val experience9 = ExperienceEffect(levels = 9)
    val paralysis = ParalysisEffect()
    val magnetism = MagnetismEffect()

    operator fun invoke() {
        AwesomePotions()
        Magnetism()
        VeinMining()
    }

}
