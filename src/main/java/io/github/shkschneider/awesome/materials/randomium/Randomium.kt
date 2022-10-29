package io.github.shkschneider.awesome.materials.randomium

object Randomium {

    const val ID = "randomium"

    class RandomiumOre : RandomiumOreBlock("${ID}_ore")

    class DeepslateRandomiumOre : RandomiumOreBlock("deepslate_${ID}_ore")

    class EndRandomiumOre : RandomiumOreBlock("end_${ID}_ore")

    operator fun invoke() = Unit

}