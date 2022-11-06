package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.machines.collector.Collector
import io.github.shkschneider.awesome.machines.crafter.Crafter
import io.github.shkschneider.awesome.machines.crusher.Crusher
import io.github.shkschneider.awesome.machines.infuser.Infuser
import io.github.shkschneider.awesome.machines.refinery.Refinery
import io.github.shkschneider.awesome.machines.smelter.Smelter

object AwesomeMachines {

    val collector = Collector()
    val crafter = Crafter()

    val refinery = Refinery()
    val crusher = Crusher()
    val infuser = Infuser()
    val smelter = Smelter()

    operator fun invoke() {
        MachineTest()
    }

}
