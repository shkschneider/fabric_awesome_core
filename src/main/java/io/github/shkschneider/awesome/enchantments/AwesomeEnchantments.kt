package io.github.shkschneider.awesome.enchantments

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.custom.Magnetism
import io.github.shkschneider.awesome.custom.VeinMining

object AwesomeEnchantments {

    private lateinit var EXPERIENCE: AwesomeEnchantment
    val experience get() = EXPERIENCE
    private lateinit var ICE_ASPECT: AwesomeEnchantment
    val iceAspect get() = ICE_ASPECT
    private lateinit var MAGNETISM: AwesomeEnchantment
    val magnetism get() = MAGNETISM
    private lateinit var POISON_ASPECT: AwesomeEnchantment
    val poisonAspect get() = POISON_ASPECT
    private lateinit var VEIN_MINING: AwesomeEnchantment
    val veinMining get() = VEIN_MINING

    operator fun invoke() {
        if (Awesome.CONFIG.enchantments.experience) {
            EXPERIENCE = ExperienceEnchantment()
        }
        ICE_ASPECT = IceAspectEnchantment()
        if (Awesome.CONFIG.enchantments.magnetism) {
            MAGNETISM = MagnetismEnchantment()
            Magnetism()
        }
        POISON_ASPECT = PoisonAspectEnchantment()
        if (Awesome.CONFIG.enchantments.veinMining) {
            VEIN_MINING = VeinMiningEnchantment()
            VeinMining()
        }
    }

}
