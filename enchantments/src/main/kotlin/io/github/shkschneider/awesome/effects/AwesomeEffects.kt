package io.github.shkschneider.awesome.effects

import io.github.shkschneider.awesome.core.AwesomeEffect
import io.github.shkschneider.awesome.custom.Minecraft

object AwesomeEffects {

    private lateinit var _paralysis: AwesomeEffect
    val paralysis get() = _paralysis

    operator fun invoke(): List<AwesomeEffect> = buildList {
        // TODO activate or not? needs an Enchantment
        if (Minecraft.isDevelopment) _paralysis = ParalysisEffect()
    }

}
