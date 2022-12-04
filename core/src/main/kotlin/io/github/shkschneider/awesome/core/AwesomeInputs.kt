package io.github.shkschneider.awesome.core

import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

object AwesomeInputs {

    val keyboard = InputUtil.Type.KEYSYM
    val mouse = InputUtil.Type.MOUSE

    fun shift(): Boolean =
        Screen.hasShiftDown()

    fun control(): Boolean =
        Screen.hasControlDown()

    fun alt(): Boolean =
        Screen.hasAltDown()

    val enter = GLFW.GLFW_KEY_ENTER
    // ...

}

