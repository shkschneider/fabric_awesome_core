package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.enchantments.CriticalEnchantment
import io.github.shkschneider.awesome.enchantments.IceAspectEnchantment
import io.github.shkschneider.awesome.enchantments.LastStandEnchantment
import io.github.shkschneider.awesome.enchantments.MagnetismEnchantment
import io.github.shkschneider.awesome.enchantments.ParalysisEnchantment
import io.github.shkschneider.awesome.enchantments.PoisonAspectEnchantment
import io.github.shkschneider.awesome.enchantments.SixthSenseEnchantment
import io.github.shkschneider.awesome.enchantments.UnbreakableEnchantment
import io.github.shkschneider.awesome.enchantments.VampirismEnchantment
import io.github.shkschneider.awesome.enchantments.VeinMiningEnchantment

object AwesomeEnchantments {

    private lateinit var _critical: AwesomeEnchantment
    val critical get() = _critical
    private lateinit var _iceAspect: AwesomeEnchantment
    val iceAspect get() = _iceAspect
    private lateinit var _lastStand: AwesomeEnchantment
    val lastStand get() = _lastStand
    private lateinit var _magnetism: AwesomeEnchantment
    val magnetism get() = _magnetism
    private lateinit var _paralysis: AwesomeEnchantment
    val paralysis get() = _paralysis
    private lateinit var _poisonAspect: AwesomeEnchantment
    val poisonAspect get() = _poisonAspect
    private lateinit var _sixthSense: AwesomeEnchantment
    val sixthSense get() = _sixthSense
    private lateinit var _unbreakable: AwesomeEnchantment
    val unbreakable get() = _unbreakable
    private lateinit var _vampirism: AwesomeEnchantment
    val vampirism get() = _vampirism
    private lateinit var _veinMining: AwesomeEnchantment
    val veinMining get() = _veinMining

    operator fun invoke() {
        if (Awesome.CONFIG.enchantments.critical) _critical = CriticalEnchantment()
        if (Awesome.CONFIG.enchantments.iceAspect) _iceAspect = IceAspectEnchantment()
        if (Awesome.CONFIG.enchantments.lastStand) _lastStand = LastStandEnchantment()
        if (Awesome.CONFIG.enchantments.magnetism) _magnetism = MagnetismEnchantment()
        if (Awesome.CONFIG.enchantments.paralysis) _paralysis = ParalysisEnchantment()
        if (Awesome.CONFIG.enchantments.poisonAspect) _poisonAspect = PoisonAspectEnchantment()
        if (Awesome.CONFIG.enchantments.sixthSense) _sixthSense = SixthSenseEnchantment()
        if (Awesome.CONFIG.enchantments.unbreakable) _unbreakable = UnbreakableEnchantment()
        if (Awesome.CONFIG.enchantments.vampirism) _vampirism = VampirismEnchantment()
        if (Awesome.CONFIG.enchantments.veinMining) _veinMining = VeinMiningEnchantment()
    }

}
