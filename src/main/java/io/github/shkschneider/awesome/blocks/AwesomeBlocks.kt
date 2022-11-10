package io.github.shkschneider.awesome.blocks

import io.github.shkschneider.awesome.Awesome

object AwesomeBlocks {

    val frame = FrameBlock()
    val tesseract = TesseractBlock()

    object Randomium {

        const val ID = "randomium"

        val ore = RandomiumOre("${ID}_ore")
        val deepslateOre = RandomiumOre("deepslate_${ID}_ore")
        val endOre = RandomiumOre("end_${ID}_ore")

        operator fun invoke() = Unit

    }

    operator fun invoke() {
        if (Awesome.CONFIG.randomiumOre) {
            Randomium()
        }
    }

}
