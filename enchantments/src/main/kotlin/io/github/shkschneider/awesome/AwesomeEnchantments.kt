package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.custom.SilkTouchSpawners
import io.github.shkschneider.awesome.effects.AwesomeEffects
import io.github.shkschneider.awesome.enchantments.IceAspectEnchantment
import io.github.shkschneider.awesome.enchantments.MagnetismEnchantment
import io.github.shkschneider.awesome.enchantments.PoisonAspectEnchantment
import io.github.shkschneider.awesome.enchantments.SixthSenseEnchantment
import io.github.shkschneider.awesome.enchantments.UnbreakableEnchantment
import io.github.shkschneider.awesome.enchantments.VeinMiningEnchantment

object AwesomeEnchantments {

    private lateinit var _iceAspect: AwesomeEnchantment
    val iceAspect get() = _iceAspect
    private lateinit var _magnetism: AwesomeEnchantment
    val magnetism get() = _magnetism
    private lateinit var _poisonAspect: AwesomeEnchantment
    val poisonAspect get() = _poisonAspect
    private lateinit var _sixthSense: AwesomeEnchantment
    val sixthSense get() = _sixthSense
    private lateinit var _unbreakable: AwesomeEnchantment
    val unbreakable get() = _unbreakable
    private lateinit var _veinMining: AwesomeEnchantment
    val veinMining get() = _veinMining

    operator fun invoke() {
        AwesomeEffects()
        if (Awesome.CONFIG.enchantments.magnetism) _magnetism = MagnetismEnchantment()
        if (Awesome.CONFIG.enchantments.unbreakable) _unbreakable = UnbreakableEnchantment()
        if (Awesome.CONFIG.enchantments.sixthSense) _sixthSense = SixthSenseEnchantment()
        if (Awesome.CONFIG.enchantments.veinMining) _veinMining = VeinMiningEnchantment()
        if (Awesome.CONFIG.enchantments.aspects) {
            _iceAspect = IceAspectEnchantment()
            _poisonAspect = PoisonAspectEnchantment()
        }
        if (Awesome.CONFIG.enchantments.silkTouchSpawners) SilkTouchSpawners()
    }

}
