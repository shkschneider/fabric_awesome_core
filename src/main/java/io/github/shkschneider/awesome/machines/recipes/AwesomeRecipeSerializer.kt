package io.github.shkschneider.awesome.machines.recipes

import com.google.gson.JsonObject
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.ShapedRecipe
import net.minecraft.util.Identifier
import net.minecraft.util.JsonHelper
import net.minecraft.util.collection.DefaultedList

class AwesomeRecipeSerializer<T : Recipe<*>>(
    private val size: Int,
    private val constructor: (DefaultedList<Ingredient>, ItemStack) -> T,
) : RecipeSerializer<T> {

    override fun read(id: Identifier, json: JsonObject): T {
        val output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"))
        val ingredients = JsonHelper.getArray(json, if (size > 1) "ingredients" else "ingredient")
        val inputs = DefaultedList.ofSize(size, Ingredient.EMPTY)
        (0 until inputs.size).forEach { i ->
            inputs[i] = Ingredient.fromJson(ingredients[i])
        }
        return constructor(inputs, output)
    }

    override fun read(id: Identifier, buf: PacketByteBuf): T {
        val inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY)
        (0 until inputs.size).forEach { i ->
            inputs[i] = Ingredient.fromPacket(buf)
        }
        val output = buf.readItemStack()
        return constructor(inputs, output)
    }

    override fun write(buf: PacketByteBuf, recipe: T) {
        buf.writeInt(recipe.ingredients.size)
        recipe.ingredients.forEach { ingredient ->
            ingredient.write(buf)
        }
        buf.writeItemStack(recipe.output)
    }

}