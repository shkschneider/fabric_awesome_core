package io.github.shkschneider.awesome.extras.blocks

import io.github.shkschneider.awesome.Awesome

object AwesomeBlocks {

    object Randomium {

        const val ID = "randomium"

        val ore = RandomiumOre("${ID}_ore")
        val deepslateOre = RandomiumOre("deepslate_${ID}_ore")
        val endOre = RandomiumOre("end_${ID}_ore")

        operator fun invoke() = Unit

    }

    operator fun invoke() {
        BuddingLapisBlock()
        BuddingRedstoneBlock()
        if (Awesome.CONFIG.extras.randomium) Randomium()
    }

}
