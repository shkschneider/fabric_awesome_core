package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.machines.crusher.Crusher
import io.github.shkschneider.awesome.machines.infuser.Infuser

object AwesomeMachines {

    operator fun invoke() {
        Crusher()
        Infuser()
    }

}
