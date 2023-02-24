package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.extras.SleepingHeals
import io.github.shkschneider.awesome.extras.ZenithNadirLengths
import io.github.shkschneider.awesome.extras.baguette.Baguette
import io.github.shkschneider.awesome.extras.crate.Crate
import io.github.shkschneider.awesome.extras.elevator.Elevator
import io.github.shkschneider.awesome.extras.entities.AwesomeEntities
import io.github.shkschneider.awesome.extras.lilypad.LilyPad
import io.github.shkschneider.awesome.extras.randomium.Randomium
import io.github.shkschneider.awesome.extras.rope.Rope
import io.github.shkschneider.awesome.extras.scythe.Scythe
import io.github.shkschneider.awesome.extras.tool.AwesomeTools
import io.github.shkschneider.awesome.extras.void.Void

object AwesomeExtras {

    operator fun invoke() {
        if (Awesome.CONFIG.extras.allInOneTools) AwesomeTools()
        if (Awesome.CONFIG.extras.baguette) Baguette()
        if (Awesome.CONFIG.extras.crate) Crate()
        if (Awesome.CONFIG.extras.elevator) Elevator()
        if (Awesome.CONFIG.extras.entities) AwesomeEntities()
        if (Awesome.CONFIG.extras.lilypad) LilyPad()
        if (Awesome.CONFIG.extras.randomium) Randomium()
        if (Awesome.CONFIG.extras.rope) Rope()
        if (Awesome.CONFIG.extras.scythe) Scythe()
        if (Awesome.CONFIG.extras.sleepingHeals) SleepingHeals()
        if (Awesome.CONFIG.extras.void) Void()
        if (Awesome.CONFIG.extras.zenithLengthInDays + Awesome.CONFIG.extras.nadirLengthInDays > 0F) ZenithNadirLengths()
    }

}
