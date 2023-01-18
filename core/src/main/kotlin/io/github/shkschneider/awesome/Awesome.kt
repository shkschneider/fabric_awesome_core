package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.core.AwesomeConfigFactory
import io.github.shkschneider.awesome.core.AwesomeItem
import io.github.shkschneider.awesome.core.AwesomeRegistries
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups

object Awesome {

    val ID = Awesome::class.java.simpleName.lowercase()

    private class GroupItem : AwesomeItem(AwesomeUtils.identifier(ID), FabricItemSettings(), ItemGroups.INGREDIENTS)

    val GROUP: ItemGroup = AwesomeRegistries.group(AwesomeUtils.identifier(ID), GroupItem())
    val CONFIG = AwesomeConfigFactory<AwesomeConfig>(ID)(AwesomeConfig::class.java)

    operator fun invoke() = Unit

}
