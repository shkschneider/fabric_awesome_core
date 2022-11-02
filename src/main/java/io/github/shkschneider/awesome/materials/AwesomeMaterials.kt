package io.github.shkschneider.awesome.materials

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.materials.coal.CoalChip
import io.github.shkschneider.awesome.materials.coal.CoalDust
import io.github.shkschneider.awesome.materials.coal.CoalPowder
import io.github.shkschneider.awesome.materials.copper.CopperChip
import io.github.shkschneider.awesome.materials.copper.CopperDust
import io.github.shkschneider.awesome.materials.copper.CopperPowder
import io.github.shkschneider.awesome.materials.diamond.DiamondChip
import io.github.shkschneider.awesome.materials.diamond.DiamondDust
import io.github.shkschneider.awesome.materials.diamond.DiamondPowder
import io.github.shkschneider.awesome.materials.emerald.EmeraldChip
import io.github.shkschneider.awesome.materials.emerald.EmeraldDust
import io.github.shkschneider.awesome.materials.emerald.EmeraldPowder
import io.github.shkschneider.awesome.materials.frame.FrameBlock
import io.github.shkschneider.awesome.materials.gold.GoldChip
import io.github.shkschneider.awesome.materials.gold.GoldDust
import io.github.shkschneider.awesome.materials.gold.GoldPowder
import io.github.shkschneider.awesome.materials.iron.IronChip
import io.github.shkschneider.awesome.materials.iron.IronDust
import io.github.shkschneider.awesome.materials.iron.IronPowder
import io.github.shkschneider.awesome.materials.lapis.LapisChip
import io.github.shkschneider.awesome.materials.lapis.LapisDust
import io.github.shkschneider.awesome.materials.lapis.LapisPowder
import io.github.shkschneider.awesome.materials.quartz.QuartzChip
import io.github.shkschneider.awesome.materials.quartz.QuartzDust
import io.github.shkschneider.awesome.materials.quartz.QuartzPowder
import io.github.shkschneider.awesome.materials.randomium.Randomium
import io.github.shkschneider.awesome.materials.redstone.RedstoneChip
import io.github.shkschneider.awesome.materials.redstone.RedstoneFlux
import io.github.shkschneider.awesome.materials.redstone.RedstonePowder
import io.github.shkschneider.awesome.materials.tesseract.TesseractBlock
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.Items
import net.minecraft.util.registry.Registry

object AwesomeMaterials {

    val copperChip = CopperChip()
    val copperDust = CopperDust()
    val copperPowder = CopperPowder()

    val coalChip = CoalChip()
    val coalDust = CoalDust()
    val coalPowder = CoalPowder()

    val diamondChip = DiamondChip()
    val diamondDust = DiamondDust()
    val diamondPowder = DiamondPowder()

    val emeraldChip = EmeraldChip()
    val emeraldDust = EmeraldDust()
    val emeraldPowder = EmeraldPowder()

    val goldChip = GoldChip()
    val goldDust = GoldDust()
    val goldPowder = GoldPowder()

    val ironChip = IronChip()
    val ironDust = IronDust()
    val ironPowder = IronPowder()

    val lapisChip = LapisChip()
    val lapisDust = LapisDust()
    val lapisPowder = LapisPowder()

    val quartzChip = QuartzChip()
    val quartzDust = QuartzDust()
    val quartzPowder = QuartzPowder()

    val randomiumOre = Randomium.RandomiumOre()
    val deepslateRandomiumOre = Randomium.DeepslateRandomiumOre()
    val endRandomiumOre = Randomium.EndRandomiumOre()

    val redstoneChip = RedstoneChip()
    val redstoneDust = Items.REDSTONE
    val redstoneFlux = RedstoneFlux()
    val redstonePowder = RedstonePowder()

    val frame = FrameBlock()
    val tesseract = TesseractBlock()

    operator fun invoke() = Unit

    operator fun invoke(id: String, block: Block, group: ItemGroup = Awesome.GROUP) {
        invoke(id, BlockItem(block, FabricItemSettings().group(group)) as Item)
        Registry.register(Registry.BLOCK, AwesomeUtils.identifier(id), block)
    }

    operator fun invoke(id: String, item: Item) {
        Registry.register(Registry.ITEM, AwesomeUtils.identifier(id), item)
    }

}
