package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.blocks.RandomiumOre
import io.github.shkschneider.awesome.blocks.TesseractBlock

object AwesomeBlocks {

    object Randomium {

        const val ID = "randomium"

        val ore = RandomiumOre("${ID}_ore")
        val deepslateOre = RandomiumOre("deepslate_${ID}_ore")
        val endOre = RandomiumOre("end_${ID}_ore")

        operator fun invoke() = Unit

    }

    operator fun invoke() {
        TesseractBlock()
        if (Awesome.CONFIG.worldGen.randomium) Randomium()
    }

}
