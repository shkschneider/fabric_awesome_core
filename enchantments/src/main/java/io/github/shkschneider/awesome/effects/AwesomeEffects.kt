package io.github.shkschneider.awesome.effects

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeEffect
import io.github.shkschneider.awesome.core.Minecraft

object AwesomeEffects {

    private lateinit var EXPERIENCE_1: AwesomeEffect
    val experience1 get() = EXPERIENCE_1
    private lateinit var EXPERIENCE_9: AwesomeEffect
    val experience9 get() = EXPERIENCE_9
    private lateinit var MAGNETISM: AwesomeEffect
    val magnetism get() = MAGNETISM
    private lateinit var PARALYSIS: AwesomeEffect
    val paralysis get() = PARALYSIS

    operator fun invoke(): List<AwesomeEffect> = buildList {
        if (Minecraft.isDevelopment) {
            PARALYSIS = ParalysisEffect()
        }
        if (Awesome.CONFIG.potions.experience) {
            EXPERIENCE_1 = ExperienceEffect(levels = 1)
            EXPERIENCE_9 = ExperienceEffect(levels = 9)
        }
        if (Awesome.CONFIG.enchantments.magnetism) {
            MAGNETISM = MagnetismEffect()
        }
    }

}
