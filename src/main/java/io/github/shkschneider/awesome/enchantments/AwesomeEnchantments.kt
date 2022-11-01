package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.AwesomeConfig

object AwesomeEnchantments {

    lateinit var experience: AwesomeEnchantment
    lateinit var iceAspect: AwesomeEnchantment
    lateinit var magnetism: AwesomeEnchantment
    lateinit var poisonAspect: AwesomeEnchantment
    lateinit var veinMining: AwesomeEnchantment

    operator fun invoke() {
        if (AwesomeConfig.oreDropXpWithExperienceEnchantment) {
            experience = ExperienceEnchantment()
        }
        iceAspect = IceAspectEnchantment()
        if (AwesomeConfig.magnetismEnchantment) {
            magnetism = MagnetismEnchantment()
        }
        poisonAspect = PoisonAspectEnchantment()
        if (AwesomeConfig.veinMiningEnchantment) {
            veinMining = VeinMiningEnchantment()
        }
    }

}