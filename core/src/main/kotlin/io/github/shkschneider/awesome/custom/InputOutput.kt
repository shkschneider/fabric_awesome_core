package io.github.shkschneider.awesome.custom

data class InputOutput(
    var inputs: Pair<Int, List<Faces>> = 0 to emptyList(),
    val outputs: Pair<Int, List<Faces>> = 0 to emptyList(),
) {

    val size: Int = inputs.first + outputs.first

    fun isInput(slot: Int): Boolean =
        slot in (0 until inputs.first)

    fun canInsert(slot: Int, face: Faces): Boolean =
        isInput(slot) && face in inputs.second

    fun isOutput(slot: Int): Boolean =
        slot in (inputs.first until size)

    fun canExtract(slot: Int, face: Faces): Boolean =
        isOutput(slot) && face in outputs.second

}
