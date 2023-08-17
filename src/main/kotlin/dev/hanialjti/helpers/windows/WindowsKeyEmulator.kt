package dev.hanialjti.helpers.windows

import dev.hanialjti.helpers.KeyEmulator
import dev.hanialjti.models.Key

object WindowsKeyEmulator : KeyEmulator {

    private val user32 = User32.INSTANCE

    override fun press(key: Key) {
        key.windowsCode?.let {
            user32.keybd_event(it, 0, 0, 0)
        } ?: println("Could not press button: $key")
    }

    override fun release(key: Key) {
        key.windowsCode?.let {
            user32.keybd_event(it, 0, 2, 0)
        } ?: println("Could not release button: $key")
    }

    override fun pressAndRelease(key: Key) {
        press(key)
        release(key)
    }

    override fun pressAndRelease(vararg keys: Key) {
        for (key in keys) {
            press(key)
        }

        for (key in keys) {
            release(key)
        }
    }
}