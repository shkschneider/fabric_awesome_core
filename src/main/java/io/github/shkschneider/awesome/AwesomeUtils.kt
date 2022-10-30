package io.github.shkschneider.awesome

object AwesomeUtils {

    const val STACK = 64

    fun key(vararg k: String) = "${Awesome.ID}_${k.joinToString(separator = "_") { it.lowercase() }}"

    fun translatable(vararg k: String) = "${k.first().lowercase()}.${Awesome.ID}.${k.drop(1).joinToString(separator = ".") { it.lowercase() }}"

}