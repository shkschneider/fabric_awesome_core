package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.items.AwesomeItems
import io.github.shkschneider.awesome.machines.collector.Collector
import io.github.shkschneider.awesome.machines.crafter.Crafter
import io.github.shkschneider.awesome.machines.crusher.Crusher
import io.github.shkschneider.awesome.machines.infuser.Infuser
import io.github.shkschneider.awesome.machines.quarry.Quarry
import io.github.shkschneider.awesome.machines.recycler.Recycler
import io.github.shkschneider.awesome.machines.refinery.Refinery
import io.github.shkschneider.awesome.machines.smelter.Smelter

object AwesomeMachines {

    val fuel = Awesome.flux

    val collector = Collector()
    val crafter = Crafter()
    val crusher = Crusher()
    val infuser = Infuser()
    val recycler = Recycler()
    val refinery = Refinery()
    val smelter = Smelter()

    private lateinit var _quarry: Quarry
    val quarry get() = _quarry

    operator fun invoke() {
        if (Awesome.CONFIG.machines.quarry) _quarry = Quarry()
        AwesomeItems()
    }

}
