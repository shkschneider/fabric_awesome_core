package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.machines.collector.Collector
import io.github.shkschneider.awesome.machines.crusher.Crusher
import io.github.shkschneider.awesome.machines.infuser.Infuser
import io.github.shkschneider.awesome.machines.smelter.Smelter

object AwesomeMachines {

    val collector = Collector()

    val crusher = Crusher()
    val infuser = Infuser()
    val smelter = Smelter()

    operator fun invoke() = Unit

}
