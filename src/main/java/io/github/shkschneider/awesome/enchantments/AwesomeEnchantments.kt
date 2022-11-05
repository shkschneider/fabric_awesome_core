package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeEnchantment

object AwesomeEnchantments {

    lateinit var experience: AwesomeEnchantment
    lateinit var iceAspect: AwesomeEnchantment
    lateinit var magnetism: AwesomeEnchantment
    lateinit var poisonAspect: AwesomeEnchantment
    lateinit var veinMining: AwesomeEnchantment

    operator fun invoke() {
        if (Awesome.CONFIG.oreDropXpWithExperienceEnchantment) {
            experience = ExperienceEnchantment()
        }
        iceAspect = IceAspectEnchantment()
        if (Awesome.CONFIG.magnetismEnchantment) {
            magnetism = MagnetismEnchantment()
        }
        poisonAspect = PoisonAspectEnchantment()
        if (Awesome.CONFIG.veinMiningEnchantment) {
            veinMining = VeinMiningEnchantment()
        }
    }

}
