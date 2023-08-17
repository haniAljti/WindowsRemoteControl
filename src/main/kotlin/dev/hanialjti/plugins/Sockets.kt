package dev.hanialjti.plugins

import dev.hanialjti.helpers.KeyEmulator
import dev.hanialjti.helpers.MouseEmulator
import dev.hanialjti.models.Key
import dev.hanialjti.models.MouseKey
import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection
import java.time.Duration


fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
    }
    routing {
        webSocket("/ws") { // websocketSession
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    val text = frame.readText()
                    outgoing.send(Frame.Text("YOU SAID: $text"))
                    if (text.equals("bye", ignoreCase = true)) {
                        close(CloseReason(CloseReason.Codes.NORMAL, "Client said BYE"))
                    }
                }
            }
        }
        webSocket("/") {
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    val message = frame.readText()
                    println("Event received: $message")

                    val event = Json.decodeFromString<ControlEvent>(message)
                    handleEvent(event)
                }
            }
        }
    }
}

fun handleEvent(event: ControlEvent) {

    val mouseEmulator = MouseEmulator.getInstance()
    val keyEmulator = KeyEmulator.getInstance()

    when (event.event) {

        Event.MouseMove.code -> {
            val x = event.params[Param.XCoordinate.code]?.toInt() ?: 0
            val y = event.params[Param.YCoordinate.code]?.toInt() ?: 0

            mouseEmulator.moveRelative(x, y)
        }

        Event.Scroll.code -> {
            val delta = event.params[Param.ScrollDelta.code]?.toInt() ?: 0
            mouseEmulator.scroll(delta)
        }

        Event.Zoom.code -> {
            val delta = event.params[Param.ScrollDelta.code]?.toInt() ?: 0
            keyEmulator.press(Key.Ctrl)
            mouseEmulator.scroll(delta)
            keyEmulator.release(Key.Ctrl)
        }

        Event.MouseClickEvent.code -> {
            val keyEvent = event.params[Param.Key.code]?.toInt() ?: -1
            val actionEvent = event.params[Param.Action.code]?.toInt() ?: -1
            val mouseKey = MouseKey.fromCode(keyEvent)

            mouseKey?.let {
                when (actionEvent) {
                    Action.Down.code -> mouseEmulator.press(it)
                    Action.Up.code -> mouseEmulator.release(it)
                    Action.Click.code -> mouseEmulator.pressAndRelease(it)
                }
            }
        }

        Event.TextInput.code -> {
            val text = event.params[Param.Text.code] ?: ""

            val stringSelection = StringSelection(text)
            val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
            clipboard.setContents(stringSelection, stringSelection)

            keyEmulator.pressAndRelease(Key.Ctrl, Key.V)
        }


        Event.KeyInput.code -> {
            val keyEvent = event.params[Param.Key.code]?.toInt() ?: -1
            val key = Key.fromCode(keyEvent)

            if (key == null) {
                println("Invalid key: $keyEvent")
            }

            key?.let { keyEmulator.pressAndRelease(it) }
        }

        Event.ShortCutInput.code -> {
            val keysString = event.params[Param.Key.code] ?: ""
            val keys = keysString.split(",").mapNotNull {
                Key.fromCode(it.toInt())
            }
            keyEmulator.pressAndRelease(*keys.toTypedArray())
        }

    }
}

@Serializable
data class ControlEvent(
    val event: Int,
    val params: Map<Int, String>
)

enum class Event(val code: Int) {
    MouseMove(1),
    Scroll(2),
    Zoom(3),
    MouseClickEvent(4),
    KeyInput(5),
    TextInput(6),
    ShortCutInput(7),
}

enum class Param(val code: Int) {
    XCoordinate(1),
    YCoordinate(2),
    ScrollDelta(3),
    Text(4),
    Key(5),
    Action(6)
}

enum class Action(val code: Int) {
    Up(1),
    Down(2),
    Click(3)
}
