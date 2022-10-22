package io.github.shkschneider.awesome.titanium

import io.github.shkschneider.awesome.AwesomeBlocks
import io.github.shkschneider.awesome.AwesomeItemGroups
import io.github.shkschneider.awesome.AwesomeItems
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.Item

object Titanium {

    val ingot = AwesomeItems.register("titanium_ingot", Item(
        FabricItemSettings().group(AwesomeItemGroups.AWESOME)
    ))

    val nugget = AwesomeItems.register("titanium_nugget", Item(
        FabricItemSettings().group(AwesomeItemGroups.AWESOME)
    ))

    val raw = AwesomeItems.register("raw_titanium", Item(
        FabricItemSettings().group(AwesomeItemGroups.AWESOME)
    ))

    val block = AwesomeBlocks.register("titanium_block", AwesomeItemGroups.AWESOME, Block(
        FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()
    ))

    val rawBlock = AwesomeBlocks.register("raw_titanium_block", AwesomeItemGroups.AWESOME, Block(
        FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()
    ))

    val ore = AwesomeBlocks.register("titanium_ore", AwesomeItemGroups.AWESOME, Block(
        FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool()
    ))

    val deepslateOre = AwesomeBlocks.register("deepslate_titanium_ore", AwesomeItemGroups.AWESOME, Block(
        FabricBlockSettings.of(Material.STONE).strength(5f).requiresTool()
    ))

    val netherrackOre = AwesomeBlocks.register("netherrack_titanium_ore", AwesomeItemGroups.AWESOME, Block(
        FabricBlockSettings.of(Material.STONE).strength(3f).requiresTool()
    ))

    operator fun invoke() = Unit

}