package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.items.AwesomeItems
import io.github.shkschneider.awesome.machines.collector.Collector
import io.github.shkschneider.awesome.machines.crafter.Crafter
import io.github.shkschneider.awesome.machines.crusher.Crusher
import io.github.shkschneider.awesome.machines.generator.Generator
import io.github.shkschneider.awesome.machines.infuser.Infuser
import io.github.shkschneider.awesome.machines.refinery.Refinery
import io.github.shkschneider.awesome.machines.smelter.Smelter

object AwesomeMachines {

    val FUEL = AwesomeItems.Redstone.flux

    val collector = Collector()
    val crafter = Crafter()

    val generator = Generator()
    val refinery = Refinery()
    val crusher = Crusher()
    val infuser = Infuser()
    val smelter = Smelter()

    operator fun invoke() = Unit

}
