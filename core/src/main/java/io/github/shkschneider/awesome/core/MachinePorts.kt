package io.github.shkschneider.awesome.core

data class MachinePorts(
    var inputs: Pair<Int, List<Faces>>,
    val outputs: Pair<Int, List<Faces>>,
) {

    constructor(inputs: Int, outputs: Int) : this(
        inputs = inputs to listOf(Faces.Top, Faces.Side(), Faces.Front),
        outputs = outputs to listOf(Faces.Bottom, Faces.Back),
    )

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
