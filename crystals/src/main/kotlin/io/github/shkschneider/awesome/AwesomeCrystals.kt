package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.crystals.AwesomeCrystal
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

object AwesomeCrystals {

    private lateinit var _coal: AwesomeCrystal
    val coal: AwesomeCrystal get() = _coal
    private lateinit var _copper: AwesomeCrystal
    val copper: AwesomeCrystal get() = _copper
    private lateinit var _diamond: AwesomeCrystal
    val diamond: AwesomeCrystal get() = _diamond
    private lateinit var _emerald: AwesomeCrystal
    val emerald: AwesomeCrystal get() = _emerald
    private lateinit var _gold: AwesomeCrystal
    val gold: AwesomeCrystal get() = _gold
    private lateinit var _iron: AwesomeCrystal
    val iron: AwesomeCrystal get() = _iron
    private lateinit var _lapis: AwesomeCrystal
    val lapis: AwesomeCrystal get() = _lapis
    private lateinit var _netherite: AwesomeCrystal
    val netherite: AwesomeCrystal get() = _netherite
    private lateinit var _quartz: AwesomeCrystal
    val quartz: AwesomeCrystal get() = _quartz
    private lateinit var _redstone: AwesomeCrystal
    val redstone: AwesomeCrystal get() = _redstone

    operator fun invoke() {
        if (Awesome.CONFIG.crystals.coal) _coal = AwesomeCrystal("coal", ItemStack(Items.COAL, 4))
        if (Awesome.CONFIG.crystals.copper) _copper = AwesomeCrystal("copper", ItemStack(Items.RAW_COPPER, 2))
        if (Awesome.CONFIG.crystals.diamond) _diamond = AwesomeCrystal("diamond", ItemStack(Items.DIAMOND, 1))
        if (Awesome.CONFIG.crystals.emerald) _emerald = AwesomeCrystal("emerald", ItemStack(Items.EMERALD, 1))
        if (Awesome.CONFIG.crystals.gold) _gold = AwesomeCrystal("gold", ItemStack(Items.RAW_GOLD, 2))
        if (Awesome.CONFIG.crystals.iron) _iron = AwesomeCrystal("iron", ItemStack(Items.RAW_IRON, 2))
        if (Awesome.CONFIG.crystals.lapis) _lapis = AwesomeCrystal("lapis", ItemStack(Items.LAPIS_LAZULI, 4))
        if (Awesome.CONFIG.crystals.netherite) _netherite = AwesomeCrystal("netherite", ItemStack(Items.ANCIENT_DEBRIS, 1))
        if (Awesome.CONFIG.crystals.quartz) _quartz = AwesomeCrystal("quartz", ItemStack(Items.QUARTZ, 2))
        if (Awesome.CONFIG.crystals.redstone) _redstone = AwesomeCrystal("redstone", ItemStack(Items.REDSTONE, 4))
    }

}
