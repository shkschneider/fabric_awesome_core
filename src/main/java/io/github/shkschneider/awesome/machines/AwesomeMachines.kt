package io.github.shkschneider.awesome.machines

import io.github.shkschneider.awesome.machines.crusher.Crusher

object AwesomeMachines {

    operator fun invoke() {
        Crusher()
    }

}
