package dev.hanialjti.helpers

import dev.hanialjti.helpers.windows.WindowsMouseEmulator
import dev.hanialjti.models.MouseKey

interface MouseEmulator {

    fun moveRelative(x: Int, y: Int)
    fun scroll(d: Int)
    fun press(mouseKey: MouseKey)
    fun release(mouseKey: MouseKey)
    fun pressAndRelease(mouseKey: MouseKey)

    companion object {
        fun getInstance() = WindowsMouseEmulator
    }
}