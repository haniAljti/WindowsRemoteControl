package dev.hanialjti.helpers

import dev.hanialjti.helpers.windows.WindowsKeyEmulator
import dev.hanialjti.models.Key

interface KeyEmulator {
    fun press(key: Key)
    fun release(key: Key)
    fun pressAndRelease(key: Key)
    fun pressAndRelease(vararg keys: Key)

    companion object {
        fun getInstance() = WindowsKeyEmulator
    }
}