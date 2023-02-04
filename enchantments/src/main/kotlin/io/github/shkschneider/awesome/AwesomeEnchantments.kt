package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.core.AwesomeEnchantment
import io.github.shkschneider.awesome.custom.OreXp
import io.github.shkschneider.awesome.custom.SilkTouchSpawners
import io.github.shkschneider.awesome.effects.AwesomeEffects
import io.github.shkschneider.awesome.enchantments.ExperienceEnchantment
import io.github.shkschneider.awesome.enchantments.IceAspectEnchantment
import io.github.shkschneider.awesome.enchantments.MagnetismEnchantment
import io.github.shkschneider.awesome.enchantments.PoisonAspectEnchantment
import io.github.shkschneider.awesome.enchantments.UnbreakableEnchantment
import io.github.shkschneider.awesome.enchantments.VeinMiningEnchantment
import io.github.shkschneider.awesome.obelisk.ObeliskBlock
import io.github.shkschneider.awesome.potions.AwesomePotions

object AwesomeEnchantments {

    private lateinit var _experience: AwesomeEnchantment
    val experience get() = _experience
    private lateinit var _iceAspect: AwesomeEnchantment
    val iceAspect get() = _iceAspect
    private lateinit var _magnetism: AwesomeEnchantment
    val magnetism get() = _magnetism
    private lateinit var _poisonAspect: AwesomeEnchantment
    val poisonAspect get() = _poisonAspect
    private lateinit var _unbreakable: AwesomeEnchantment
    val unbreakable get() = _unbreakable
    private lateinit var _veinMining: AwesomeEnchantment
    val veinMining get() = _veinMining

    operator fun invoke() {
        AwesomeEffects()
        AwesomePotions()
        if (Awesome.CONFIG.enchantments.experience) {
            _experience = ExperienceEnchantment()
        }
        if (Awesome.CONFIG.enchantments.magnetism) {
            _magnetism = MagnetismEnchantment()
        }
        if (Awesome.CONFIG.enchantments.unbreakable) {
            _unbreakable = UnbreakableEnchantment()
        }
        if (Awesome.CONFIG.enchantments.veinMining) {
            _veinMining = VeinMiningEnchantment()
        }
        if (Awesome.CONFIG.enchantments.aspects) {
            _iceAspect = IceAspectEnchantment()
            _poisonAspect = PoisonAspectEnchantment()
        }
        if (Awesome.CONFIG.enchantments.obelisk) ObeliskBlock()
        if (Awesome.CONFIG.enchantments.oreXp) OreXp()
        if (Awesome.CONFIG.enchantments.silkTouchSpawners) SilkTouchSpawners()
    }

}
