package io.github.shkschneider.awesome.extras.crates

// Inspired from the ShulkerBox -- retains inventory
object Crates {

    sealed class Sizes(val name: String, val width: Int, val height: Int) {

        val total: Int  = width * height

        object Small : Sizes("small", 9, 1)
        object Medium : Sizes("medium", 9, 3)
        object Large : Sizes("large", 9, 6)

        val backgroundHeight: Int get() = when (this) {
            Small -> 130
            Medium -> 166
            Large -> 220
        }
        val playerInventoryOffset: Int get() = when (this) {
            Small -> 48
            Medium -> 84
            Large -> 138
        }
        val playerHotbarOffset: Int get() = when (this) {
            Small -> 106
            Medium -> 142
            Large -> 196
        }

    }

    operator fun invoke() {
        Crate(Sizes.Small)
        Crate(Sizes.Medium)
        Crate(Sizes.Large)
    }

}
