package io.github.shkschneider.awesome.materials

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.Minecraft
import io.github.shkschneider.awesome.materials.blocks.FrameBlock
import io.github.shkschneider.awesome.materials.blocks.TesseractBlock
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Items
import net.minecraft.util.Rarity

object AwesomeMaterials {

    private val settings = FabricItemSettings().maxCount(Minecraft.STACK).group(Awesome.GROUP).rarity(Rarity.COMMON)

    object Copper {

        private const val ID = "copper"

        val chip = AwesomeOres.Chip(ID, settings)
        val dust = AwesomeOres.Dust(ID, settings)
        val powder = AwesomeOres.Powder(ID, settings)

    }

    object Coal {

        private const val ID = "coal"

        val chip = AwesomeOres.Chip(ID, settings)
        val dust = AwesomeOres.Dust(ID, settings)
        val powder = AwesomeOres.Powder(ID, settings)

    }

    object Diamond {

        private const val ID = "diamond"

        val chip = AwesomeOres.Chip(ID, settings)
        val dust = AwesomeOres.Dust(ID, settings)
        val powder = AwesomeOres.Powder(ID, settings)

    }

    object Emerald {

        private const val ID = "emerald"

        val chip = AwesomeOres.Chip(ID, settings)
        val dust = AwesomeOres.Dust(ID, settings)
        val powder = AwesomeOres.Powder(ID, settings)

    }

    object Gold {

        private const val ID = "gold"

        val chip = AwesomeOres.Chip(ID, settings)
        val dust = AwesomeOres.Dust(ID, settings)
        val powder = AwesomeOres.Powder(ID, settings)

    }

    object Iron {

        private const val ID = "iron"

        val chip = AwesomeOres.Chip(ID, settings)
        val dust = AwesomeOres.Dust(ID, settings)
        val powder = AwesomeOres.Powder(ID, settings)

    }

    object Lapis {

        private const val ID = "lapis"

        val chip = AwesomeOres.Chip(ID, settings)
        val dust = AwesomeOres.Dust(ID, settings)
        val powder = AwesomeOres.Powder(ID, settings)

    }

    object Quartz {

        private const val ID = "quartz"

        val chip = AwesomeOres.Chip(ID, settings)
        val dust = AwesomeOres.Dust(ID, settings)
        val powder = AwesomeOres.Powder(ID, settings)

    }

    object Randomium {

        const val ID = "randomium"

        val ore = RandomiumOre("${ID}_ore")
        val deepslateOre = RandomiumOre("deepslate_${ID}_ore")
        val endOre = RandomiumOre("end_${ID}_ore")

    }

    object Redstone {

        private const val ID = "redstone"

        val chip = AwesomeOres.Chip(ID, settings)
        val dust = Items.REDSTONE
        val flux = RedstoneFlux()
        val powder = AwesomeOres.Powder(ID, settings)

    }

    operator fun invoke() {
        FrameBlock()
        TesseractBlock()
    }

}
