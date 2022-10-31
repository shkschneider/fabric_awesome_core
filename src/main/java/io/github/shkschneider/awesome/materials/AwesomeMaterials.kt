package io.github.shkschneider.awesome.materials

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.materials.coal.CoalDust
import io.github.shkschneider.awesome.materials.coal.CrushedCoal
import io.github.shkschneider.awesome.materials.copper.CopperDust
import io.github.shkschneider.awesome.materials.copper.CrushedCopper
import io.github.shkschneider.awesome.materials.diamond.DiamondDust
import io.github.shkschneider.awesome.materials.emerald.EmeraldDust
import io.github.shkschneider.awesome.materials.frame.FrameBlock
import io.github.shkschneider.awesome.materials.gold.CrushedGold
import io.github.shkschneider.awesome.materials.gold.GoldDust
import io.github.shkschneider.awesome.materials.iron.CrushedIron
import io.github.shkschneider.awesome.materials.iron.IronDust
import io.github.shkschneider.awesome.materials.lapis.LapisDust
import io.github.shkschneider.awesome.materials.randomium.Randomium
import io.github.shkschneider.awesome.materials.redstone.RedstoneFlux
import io.github.shkschneider.awesome.materials.tesseract.TesseractBlock
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.Items
import net.minecraft.util.registry.Registry

object AwesomeMaterials {

    val crushedCopper = CrushedCopper()
    val copperDust = CopperDust()

    val crushedCoal = CrushedCoal()
    val coalDust = CoalDust()

    val diamondDust = DiamondDust()

    val emeraldDust = EmeraldDust()

    val crushedGold = CrushedGold()
    val goldDust = GoldDust()

    val crushedIron = CrushedIron()
    val ironDust = IronDust()

    val lapisDust = LapisDust()

    val randomiumOre = Randomium.RandomiumOre()
    val deepslateRandomiumOre = Randomium.DeepslateRandomiumOre()
    val endRandomiumOre = Randomium.EndRandomiumOre()

    val redstoneDust = Items.REDSTONE
    val redstoneFlux = RedstoneFlux()

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