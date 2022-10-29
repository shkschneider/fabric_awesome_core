package io.github.shkschneider.awesome.enchantments

object AwesomeEnchantments {

    val experience = ExperienceEnchantment()
    val iceAspect = IceAspectEnchantment()
    val magnetism = MagnetismEnchantment()
    val poisonAspect = PoisonAspectEnchantment()
    val veinMining = VeinMiningEnchantment()

    operator fun invoke() = Unit

}