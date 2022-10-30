package io.github.shkschneider.awesome.materials

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.AwesomeUtils
import io.github.shkschneider.awesome.materials.frame.FrameBlock
import io.github.shkschneider.awesome.materials.randomium.Randomium
import io.github.shkschneider.awesome.materials.redstone.CrushedLapisLazuli
import io.github.shkschneider.awesome.materials.redstone.CrushedRedstone
import io.github.shkschneider.awesome.materials.redstone.LapisLazuliDust
import io.github.shkschneider.awesome.materials.redstone.RedstoneFlux
import io.github.shkschneider.awesome.materials.redstone.RedstoneFluxDust
import io.github.shkschneider.awesome.materials.tesseract.TesseractBlock
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.Items
import net.minecraft.util.registry.Registry

object AwesomeMaterials {

    val crushedRedstone = CrushedRedstone()
    val redstoneDust = Items.REDSTONE

    val crushedLapisLazuli = CrushedLapisLazuli()
    val lapisLazuliDust = LapisLazuliDust()

    val redstoneFluxDust = RedstoneFluxDust()
    val redstoneFlux = RedstoneFlux()

    val randomiumOre = Randomium.RandomiumOre()
    val deepslateRandomiumOre = Randomium.DeepslateRandomiumOre()
    val endRandomiumOre = Randomium.EndRandomiumOre()

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