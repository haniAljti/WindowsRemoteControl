package dev.hanialjti.helpers.windows

import com.sun.jna.platform.win32.WinDef
import dev.hanialjti.helpers.MouseEmulator
import dev.hanialjti.models.MouseKey

object WindowsMouseEmulator: MouseEmulator {

    private val user32 = User32.INSTANCE

    private const val MOUSEEVENTF_MOVE = 0x0001
    private const val MOUSEEVENTF_ABSOLUTE = 0x8000
    private const val MOUSEEVENTF_WHEEL = 0x0800


    private const val MOUSEEVENTF_LEFTDOWN = 0x0002
    private const val MOUSEEVENTF_LEFTUP = 0x0004

    private const val MOUSEEVENTF_MIDDLEDOWN = 0x0020
    private const val MOUSEEVENTF_MIDDLEUP = 0x0040

    private const val MOUSEEVENTF_RIGHTDOWN = 0x0008
    private const val MOUSEEVENTF_RIGHTUP = 0x0010


    override fun moveRelative(x: Int, y: Int) {
        user32.mouse_event(MOUSEEVENTF_MOVE, x, y, 0, WinDef.DWORD(0))
    }

    override fun scroll(d: Int) {
        user32.mouse_event(MOUSEEVENTF_WHEEL, 0, 0, d * 100, WinDef.DWORD(0))
    }

    private fun mouseEvent(code: Int) = user32.mouse_event(code, 0, 0, 0, WinDef.DWORD(0))

    override fun press(mouseKey: MouseKey) = when (mouseKey) {
        MouseKey.LMouseButton -> mouseEvent(MOUSEEVENTF_LEFTDOWN)
        MouseKey.RMouseButton -> mouseEvent(MOUSEEVENTF_RIGHTDOWN)
        MouseKey.MMouseButton -> mouseEvent(MOUSEEVENTF_MIDDLEDOWN)
    }

    override fun release(mouseKey: MouseKey) = when (mouseKey) {
        MouseKey.LMouseButton -> mouseEvent(MOUSEEVENTF_LEFTUP)
        MouseKey.RMouseButton -> mouseEvent(MOUSEEVENTF_RIGHTUP)
        MouseKey.MMouseButton -> mouseEvent(MOUSEEVENTF_MIDDLEUP)
    }

    override fun pressAndRelease(mouseKey: MouseKey) {
        press(mouseKey)
        release(mouseKey)
    }


}