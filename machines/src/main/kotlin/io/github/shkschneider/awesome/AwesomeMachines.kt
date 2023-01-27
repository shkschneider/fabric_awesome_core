package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.items.AwesomeItems
import io.github.shkschneider.awesome.machines.collector.Collector
import io.github.shkschneider.awesome.machines.crafter.Crafter
import io.github.shkschneider.awesome.machines.crusher.Crusher
import io.github.shkschneider.awesome.machines.generator.Generator
import io.github.shkschneider.awesome.machines.infuser.Infuser
import io.github.shkschneider.awesome.machines.quarry.Quarry
import io.github.shkschneider.awesome.machines.recycler.Recycler
import io.github.shkschneider.awesome.machines.refinery.Refinery
import io.github.shkschneider.awesome.machines.smelter.Smelter

object AwesomeMachines {

    val fuel = AwesomeItems.Redstone.flux

    val collector = Collector()
    val crafter = Crafter()
    val crusher = Crusher()
    val generator = Generator()
    val infuser = Infuser()
    val recycler = Recycler()
    val refinery = Refinery()
    val smelter = Smelter()

    operator fun invoke() {
        AwesomeItems()
        if (Awesome.CONFIG.machines.quarry) Quarry()
    }

}
