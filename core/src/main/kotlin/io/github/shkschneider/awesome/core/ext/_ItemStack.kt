package io.github.shkschneider.awesome.core.ext

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.item.ItemStack

fun ItemStack.getEnchantments(): Map<Enchantment, Int> =
    EnchantmentHelper.get(this)

// EnchantmentHelper.getLevel() does not work for EnchantedBooks
fun ItemStack.getEnchantmentLevel(enchantment: Enchantment): Int =
    EnchantmentHelper.get(this).getOrDefault(enchantment, 0)
