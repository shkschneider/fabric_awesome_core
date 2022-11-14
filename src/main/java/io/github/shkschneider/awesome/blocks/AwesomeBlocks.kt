package io.github.shkschneider.awesome.blocks

import io.github.shkschneider.awesome.Awesome
import io.github.shkschneider.awesome.core.Minecraft

object AwesomeBlocks {

    object Randomium {

        const val ID = "randomium"

        val ore = RandomiumOre("${ID}_ore")
        val deepslateOre = RandomiumOre("deepslate_${ID}_ore")
        val endOre = RandomiumOre("end_${ID}_ore")

        operator fun invoke() = Unit

    }

    operator fun invoke() {
        if (Awesome.CONFIG.machines && Minecraft.isDevelopment) {
            FrameBlock()
            TesseractBlock()
        }
        if (Awesome.CONFIG.worldGen.randomium) {
            Randomium()
        }
    }

}
