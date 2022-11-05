package io.github.shkschneider.awesome.items

import io.github.shkschneider.awesome.Awesome

object AwesomeItems {

    sealed class Colors(val value: Int) {

        // https://github.com/glowredman/modularmaterials/blob/master/presets/complete/vanilla/materials.json
        object Coal : Colors(0x464646)
        object Iron : Colors(0xC8C8C8)
        object Copper : Colors(0xFF6400)
        object Gold : Colors(0xFFFF1E)
        object Redstone : Colors(0xC80000)
        object Emerald : Colors(0x50FF50)
        object Lapis : Colors(0x4646DC)
        object Diamond : Colors(0xC8FFFF)
        object Quartz : Colors(0xE6D2D2)
        object Netherite : Colors(0x4B484B)

    }

    operator fun invoke() {
        if (Awesome.CONFIG.imprisoner) {
            Imprisoner()
        }
    }

}
