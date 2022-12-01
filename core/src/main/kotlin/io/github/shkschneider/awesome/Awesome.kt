package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.core.AwesomeConfigFactory
import io.github.shkschneider.awesome.core.AwesomeItem
import io.github.shkschneider.awesome.core.AwesomeUtils
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object Awesome {

    private class Group : AwesomeItem(AwesomeUtils.identifier("awesome"), FabricItemSettings())

    val ID = Awesome::class.java.simpleName.lowercase()
    val NAME = ID.replaceFirstChar { it.uppercase() }
    private val _GROUP = Group()
    val GROUP: ItemGroup = FabricItemGroupBuilder.build(Identifier(ID, ID)) { ItemStack(_GROUP) }
    val CONFIG = AwesomeConfigFactory<AwesomeConfig>(ID)(AwesomeConfig::class.java)

    operator fun invoke() = Unit

}
