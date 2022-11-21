package io.github.shkschneider.awesome

import io.github.shkschneider.awesome.core.AwesomeConfigFactory
import io.github.shkschneider.awesome.core.RedstoneFlux
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object Awesome {

    val ID = Awesome::class.java.simpleName.lowercase()
    val NAME = ID.replaceFirstChar { it.uppercase() }
    /* TODO internal? */ val FLUX = RedstoneFlux()
    val GROUP: ItemGroup = FabricItemGroupBuilder.build(Identifier(ID, ID)) { ItemStack(FLUX) }
    val CONFIG = AwesomeConfigFactory<AwesomeConfig>(ID)(AwesomeConfig::class.java)

}
