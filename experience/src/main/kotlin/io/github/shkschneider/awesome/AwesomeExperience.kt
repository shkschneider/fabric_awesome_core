package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.experience.enchantments.ExperienceEnchantment
import io.github.shkschneider.awesome.experience.gamerules.KeepXpGameRule
import io.github.shkschneider.awesome.experience.obelisk.ObeliskBlock
import io.github.shkschneider.awesome.experience.potions.AwesomePotions

object AwesomeExperience {

    operator fun invoke() {
        KeepXpGameRule()
        if (Awesome.CONFIG.experience.obelisk) ObeliskBlock()
        if (Awesome.CONFIG.experience.oneXpPerBlock) ExperienceEnchantment()
        if (Awesome.CONFIG.experience.potions) AwesomePotions()
    }

}
