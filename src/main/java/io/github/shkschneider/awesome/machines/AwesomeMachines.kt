package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.machines.crusher.Crusher
import io.github.shkschneider.awesome.machines.infuser.Infuser
import io.github.shkschneider.awesome.machines.smelter.Smelter

object AwesomeMachines {

    operator fun invoke() {
        Crusher()
        Infuser()
        Smelter()
    }

}
