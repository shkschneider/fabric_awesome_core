package io.github.shkschneider.awesome.effects

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeEffect
import io.github.shkschneider.awesome.custom.Minecraft

object AwesomeEffects {

    private lateinit var _experience1: AwesomeEffect
    val experience1 get() = _experience1
    private lateinit var _experience9: AwesomeEffect
    val experience9 get() = _experience9
    private lateinit var _paralysis: AwesomeEffect
    val paralysis get() = _paralysis

    operator fun invoke(): List<AwesomeEffect> = buildList {
        if (Minecraft.isDevelopment) {
            _paralysis = ParalysisEffect()
        }
        if (Awesome.CONFIG.enchantments.experience) {
            _experience1 = ExperienceEffect(levels = 1)
            _experience9 = ExperienceEffect(levels = 9)
        }
    }

}
