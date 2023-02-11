package io.github.shkschneider.awesome.items

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.custom.Minecraft
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Items
import net.minecraft.util.Rarity

object AwesomeItems {

    private val settings = FabricItemSettings().maxCount(Minecraft.STACK).rarity(Rarity.COMMON)

    object Coal {

        private const val ID = "coal"

        val chip = AwesomeOres.Chip(ID, settings, Awesome.GROUP)
        val dust = AwesomeOres.Dust(ID, settings, Awesome.GROUP)
        val powder = AwesomeOres.Powder(ID, settings, Awesome.GROUP)

        operator fun invoke() = Unit

    }

    object Copper {

        private const val ID = "copper"

        val chip = AwesomeOres.Chip(ID, settings, Awesome.GROUP)
        val dust = AwesomeOres.Dust(ID, settings, Awesome.GROUP)
        val powder = AwesomeOres.Powder(ID, settings, Awesome.GROUP)
        val ingot = Items.COPPER_INGOT

        val frame = AwesomeMaterials.Frame(ID, settings, Awesome.GROUP)
        val plate = AwesomeMaterials.Plate(ID, settings, Awesome.GROUP)
        val rod = AwesomeMaterials.Rod(ID, settings, Awesome.GROUP)

        operator fun invoke() = Unit

    }

    object Diamond {

        private const val ID = "diamond"

        val chip = AwesomeOres.Chip(ID, settings, Awesome.GROUP)
        val dust = AwesomeOres.Dust(ID, settings, Awesome.GROUP)
        val powder = AwesomeOres.Powder(ID, settings, Awesome.GROUP)

        operator fun invoke() = Unit

    }

    object Emerald {

        private const val ID = "emerald"

        val chip = AwesomeOres.Chip(ID, settings, Awesome.GROUP)
        val dust = AwesomeOres.Dust(ID, settings, Awesome.GROUP)
        val powder = AwesomeOres.Powder(ID, settings, Awesome.GROUP)

        operator fun invoke() = Unit

    }

    object Gold {

        private const val ID = "gold"

        val chip = AwesomeOres.Chip(ID, settings, Awesome.GROUP)
        val dust = AwesomeOres.Dust(ID, settings, Awesome.GROUP)
        val powder = AwesomeOres.Powder(ID, settings, Awesome.GROUP)

        operator fun invoke() = Unit

    }

    object Iron {

        private const val ID = "iron"

        val chip = AwesomeOres.Chip(ID, settings, Awesome.GROUP)
        val dust = AwesomeOres.Dust(ID, settings, Awesome.GROUP)
        val powder = AwesomeOres.Powder(ID, settings, Awesome.GROUP)

        operator fun invoke() = Unit

    }

    object Lapis {

        private const val ID = "lapis"

        val chip = AwesomeOres.Chip(ID, settings, Awesome.GROUP)
        val dust = AwesomeOres.Dust(ID, settings, Awesome.GROUP)
        val powder = AwesomeOres.Powder(ID, settings, Awesome.GROUP)
        val ingot = AwesomeOres.Ingot(ID, settings, Awesome.GROUP)

        val frame = AwesomeMaterials.Frame(ID, settings, Awesome.GROUP)
        val plate = AwesomeMaterials.Plate(ID, settings, Awesome.GROUP)
        val rod = AwesomeMaterials.Rod(ID, settings, Awesome.GROUP)

        operator fun invoke() = Unit

    }

    object Netherite {

        private const val ID = "netherite"

    }

    object Quartz {

        private const val ID = "quartz"

        val chip = AwesomeOres.Chip(ID, settings, Awesome.GROUP)
        val dust = AwesomeOres.Dust(ID, settings, Awesome.GROUP)
        val powder = AwesomeOres.Powder(ID, settings, Awesome.GROUP)

        operator fun invoke() = Unit

    }

    object Redstone {

        private const val ID = "redstone"

        val chip = AwesomeOres.Chip(ID, settings, Awesome.GROUP)
        val dust = Items.REDSTONE // "wire"
        val powder = AwesomeOres.Powder(ID, settings, Awesome.GROUP)
        val ingot = AwesomeOres.Ingot(ID, settings, Awesome.GROUP)

        val frame = AwesomeMaterials.Frame(ID, settings, Awesome.GROUP)
        val plate = AwesomeMaterials.Plate(ID, settings, Awesome.GROUP)
        val rod = AwesomeMaterials.Rod(ID, settings, Awesome.GROUP)

        operator fun invoke() = Unit

    }

    //endregion

    //endregion

    operator fun invoke() {
        Copper()
        Coal()
        Diamond()
        Emerald()
        Gold()
        Iron()
        Lapis()
        Quartz()
        Redstone()
        if (Awesome.CONFIG.machines.imprisoner) Imprisoner()
        if (Awesome.CONFIG.machines.prospector) Prospector()
    }

}
