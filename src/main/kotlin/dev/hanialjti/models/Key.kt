package dev.hanialjti.models

sealed class Key(val code: Int, val windowsCode: Byte?) {
    object Enter : Key(1, 0x0D)
    object Backspace : Key(2, 0x08)
    object Ctrl : Key(3, 0x11)
    object Alt : Key(4, 0x12)
    object Tab : Key(5, 0x09)
    object Esc : Key(6, 0x1B)
    object Shift : Key(7, 0x10)
    object Insert : Key(8, 0x2D)
    object Delete : Key(9, 0x2E)
    object Home : Key(10, 0x24)
    object End : Key(11, 0x23)
    object PageUp : Key(12, 0x21)
    object PageDown : Key(13, 0x22)
    object Up : Key(14, 0x26)
    object Down : Key(15, 0x28)
    object Left : Key(16, 0x25)
    object Right : Key(17, 0x27)

    object F1 : Key(18, 0x70)
    object F2 : Key(19, 0x71)
    object F3 : Key(20, 0x72)
    object F4 : Key(21, 0x73)
    object F5 : Key(22, 0x74)
    object F6 : Key(23, 0x75)
    object F7 : Key(24, 0x76)
    object F8 : Key(25, 0x77)
    object F9 : Key(26, 0x78)
    object F10 : Key(27, 0x79)
    object F11 : Key(28, 0x7A)
    object F12 : Key(29, 0x7B)

    object A : Key(30, 0x41)
    object B : Key(31, 0x42)
    object C : Key(32, 0x43)
    object D : Key(33, 0x44)
    object E : Key(34, 0x45)
    object F : Key(35, 0x46)
    object G : Key(36, 0x47)
    object H : Key(37, 0x48)
    object I : Key(38, 0x49)
    object J : Key(39, 0x4A)
    object K : Key(40, 0x4B)
    object L : Key(41, 0x4C)
    object M : Key(42, 0x4D)
    object N : Key(43, 0x4E)
    object O : Key(44, 0x4F)
    object P : Key(45, 0x50)
    object Q : Key(46, 0x51)
    object R : Key(47, 0x52)
    object S : Key(48, 0x53)
    object T : Key(49, 0x54)
    object U : Key(50, 0x55)
    object V : Key(51, 0x56)
    object W : Key(52, 0x57)
    object X : Key(53, 0x58)
    object Y : Key(54, 0x59)
    object Z : Key(55, 0x5A)

    object Num0 : Key(56, 0x30)
    object Num1 : Key(57, 0x31)
    object Num2 : Key(58, 0x32)
    object Num3 : Key(59, 0x33)
    object Num4 : Key(60, 0x34)
    object Num5 : Key(61, 0x35)
    object Num6 : Key(62, 0x36)
    object Num7 : Key(63, 0x37)
    object Num8 : Key(64, 0x38)
    object Num9 : Key(65, 0x39)

    object PlayPauseMedia : Key(66, 0xB3.toByte())
    object StopMedia : Key(67, 0xB2.toByte())
    object PrevTrack : Key(68, 0xB0.toByte())
    object NextTrack : Key(69, 0xB1.toByte())

    object VolumeUp : Key(70, 0xAF.toByte())
    object VolumeDown : Key(71, 0xAE.toByte())
    object VolumeMute : Key(72, 0xAD.toByte())

    object BrowserBack : Key(73, 0xA6.toByte())
    object BrowserForward : Key(74, 0xA7.toByte())
    object BrowserRefresh : Key(75, 0xA8.toByte())
    object BrowserStop : Key(76, 0xA9.toByte())
    object BrowserSearch : Key(77, 0xAA.toByte())
    object BrowserFavorites : Key(78, 0xAB.toByte())
    object BrowserHome : Key(79, 0xAC.toByte())

    object Plus : Key(80, 0xBB.toByte())
    object Minus : Key(81, 0xBD.toByte())

    object Windows : Key(201, 0x5B)
    object Sleep : Key(202, 0x5F)

    companion object {
        fun fromCode(code: Int) = when (code) {
            Enter.code -> Enter
            Backspace.code -> Backspace
            Ctrl.code -> Ctrl
            Alt.code -> Alt
            Tab.code -> Tab
            Esc.code -> Esc
            Shift.code -> Shift
            Insert.code -> Insert
            Delete.code -> Delete
            Home.code -> Home
            End.code -> End
            PageUp.code -> PageUp
            PageDown.code -> PageDown
            Up.code -> Up
            Down.code -> Down
            Left.code -> Left
            Right.code -> Right

            F1.code -> F1
            F2.code -> F2
            F3.code -> F3
            F4.code -> F4
            F5.code -> F5
            F6.code -> F6
            F7.code -> F7
            F8.code -> F8
            F9.code -> F9
            F10.code -> F10
            F11.code -> F11
            F12.code -> F12

            A.code -> A
            B.code -> B
            C.code -> C
            D.code -> D
            E.code -> E
            F.code -> F
            G.code -> G
            H.code -> H
            I.code -> I
            J.code -> J
            K.code -> K
            L.code -> L
            M.code -> M
            N.code -> N
            O.code -> O
            P.code -> P
            Q.code -> Q
            R.code -> R
            S.code -> S
            T.code -> T
            U.code -> U
            V.code -> V
            W.code -> W
            X.code -> X
            Y.code -> Y
            Z.code -> Z

            Num0.code -> Num0
            Num1.code -> Num1
            Num2.code -> Num2
            Num3.code -> Num3
            Num4.code -> Num4
            Num5.code -> Num5
            Num6.code -> Num6
            Num7.code -> Num7
            Num8.code -> Num8
            Num9.code -> Num9

            PlayPauseMedia.code -> PlayPauseMedia
            StopMedia.code -> StopMedia
            PrevTrack.code -> PrevTrack
            NextTrack.code -> NextTrack

            VolumeUp.code -> VolumeUp
            VolumeDown.code -> VolumeDown
            VolumeMute.code -> VolumeMute

            BrowserBack.code -> BrowserBack
            BrowserForward.code -> BrowserForward
            BrowserRefresh.code -> BrowserRefresh
            BrowserStop.code -> BrowserStop
            BrowserSearch.code -> BrowserSearch
            BrowserFavorites.code -> BrowserFavorites
            BrowserHome.code -> BrowserHome

            Plus.code -> Plus
            Minus.code -> Minus

            Windows.code -> Windows
            Sleep.code -> Sleep

            else -> null
        }
    }
}

sealed class MouseKey(val code: Int) {
    object LMouseButton : MouseKey(1)
    object RMouseButton : MouseKey(2)
    object MMouseButton : MouseKey(3)

    companion object {
        fun fromCode(code: Int) = when (code) {
            LMouseButton.code -> LMouseButton
            RMouseButton.code -> RMouseButton
            MMouseButton.code -> MMouseButton
            else -> null
        }
    }
}