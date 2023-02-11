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
        if (Awesome.CONFIG.crystals.coal > 0) _coal = AwesomeCrystal("coal", ItemStack(Items.COAL, Awesome.CONFIG.crystals.coal))
        if (Awesome.CONFIG.crystals.copper > 0) _copper = AwesomeCrystal("copper", ItemStack(Items.RAW_COPPER, Awesome.CONFIG.crystals.coal))
        if (Awesome.CONFIG.crystals.diamond > 0) _diamond = AwesomeCrystal("diamond", ItemStack(Items.DIAMOND, Awesome.CONFIG.crystals.diamond))
        if (Awesome.CONFIG.crystals.emerald > 0) _emerald = AwesomeCrystal("emerald", ItemStack(Items.EMERALD, Awesome.CONFIG.crystals.emerald))
        if (Awesome.CONFIG.crystals.gold > 0) _gold = AwesomeCrystal("gold", ItemStack(Items.RAW_GOLD, Awesome.CONFIG.crystals.gold))
        if (Awesome.CONFIG.crystals.iron > 0) _iron = AwesomeCrystal("iron", ItemStack(Items.RAW_IRON, Awesome.CONFIG.crystals.iron))
        if (Awesome.CONFIG.crystals.lapis > 0) _lapis = AwesomeCrystal("lapis", ItemStack(Items.LAPIS_LAZULI, Awesome.CONFIG.crystals.lapis))
        if (Awesome.CONFIG.crystals.netherite > 0) _netherite = AwesomeCrystal("netherite", ItemStack(Items.ANCIENT_DEBRIS, Awesome.CONFIG.crystals.netherite))
        if (Awesome.CONFIG.crystals.quartz > 0) _quartz = AwesomeCrystal("quartz", ItemStack(Items.QUARTZ, Awesome.CONFIG.crystals.quartz))
        if (Awesome.CONFIG.crystals.redstone > 0) _redstone = AwesomeCrystal("redstone", ItemStack(Items.REDSTONE, Awesome.CONFIG.crystals.redstone))
    }

}
