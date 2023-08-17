package dev.hanialjti.helpers.windows

import com.sun.jna.Native
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.win32.StdCallLibrary


interface User32 : StdCallLibrary {
    companion object {
        val INSTANCE: User32 = Native.load("user32", User32::class.java)
    }

    fun keybd_event(bVk: Byte, bScan: Byte, dwFlags: Int, dwExtraInfo: Int)
    fun GetCursorPos(lpPoint: WinDef.POINT): Boolean
    fun SetCursorPos(x: Int, y: Int): Boolean
    fun mouse_event(dwFlags: Int, dx: Int, dy: Int, dwData: Int, dwExtraInfo: WinDef.DWORD)
}