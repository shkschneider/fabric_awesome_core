package io.github.shkschneider.awesome.effects

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeEffect
import io.github.shkschneider.awesome.custom.Magnetism
import io.github.shkschneider.awesome.custom.VeinMining

object AwesomeEffects {

    private lateinit var _experience1: AwesomeEffect
    val experience1 get() = _experience1
    private lateinit var _experience9: AwesomeEffect
    val experience9 get() = _experience9
    val paralysis = ParalysisEffect()
    val magnetism = MagnetismEffect()

    operator fun invoke(): List<AwesomeEffect> = buildList {
        if (Awesome.CONFIG.experiencePotions) {
            _experience1 = ExperienceEffect(levels = 1)
            _experience9 = ExperienceEffect(levels = 9)
        }
        Magnetism()
        VeinMining()
    }

}
