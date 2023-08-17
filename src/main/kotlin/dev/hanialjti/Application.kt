package dev.hanialjti

import dev.hanialjti.plugins.*
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.server.application.*
import io.ktor.utils.io.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import java.awt.*
import java.io.IOException
import java.net.InetAddress
import javax.jmdns.JmDNS
import javax.jmdns.ServiceInfo
import kotlin.system.exitProcess


fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)
//    registerSocketService()
//    runBlocking {
//        val selectorManager = SelectorManager(Dispatchers.IO)
//        val socket = aSocket(selectorManager).udp().bind(InetSocketAddress(InetAddress.getLocalHost().hostName, 6123))
//        println("Server is listening at ${socket.localAddress}")
////      while (true) {
////      println("Accepted $socket")
//        launch {
//            val receiveChannel = socket.incoming
//            val sendChannel = socket.outgoing
//            println(receiveChannel.receive().address)
//            var packet = ""
//            receiveChannel.consumeAsFlow().collect {
//                val bytes = it.packet.readBytes()
//                val json = String(bytes)
//                println(json)
//                val event = Json.decodeFromString<ControlEvent>(json)
//                handleEvent(event)
//            }
////            while (true) {
////                receiveChannel.receive().packet.readBytes().let { text ->
////                    println(String(text))
//////                    if (packet.isBlank() && !text.startsWith("{")) {
//////                        return@let
//////                    }
//////                    packet += text
//////                    if (packet.endsWith("}")) {
//////                        val event = Json.decodeFromString<ControlEvent>(text)
//////                        handleEvent(event)
//////                        packet = ""
//////                    }
////                }
////            }
////          } catch (e: Throwable) {
////              socket.close()
////          }
//        }
//    }
//}

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureTemplating()
    configureSockets()
    configureRouting()
    registerSocketService()
    addSystemTrayIcon()
}

fun registerSocketService() {
    try {
        // Create a JmDNS instance
        val jmdns = JmDNS.create(InetAddress.getLocalHost())

        // Register a service
        val serviceInfo =
            ServiceInfo.create("_remote._tcp.", InetAddress.getLocalHost().hostName, 6123, "path=index.html")
        jmdns.registerService(serviceInfo)
    } catch (e: IOException) {
        println(e.message)
    }
}

fun addSystemTrayIcon() {

    if (!SystemTray.isSupported()) {
        println("SystemTray is not supported")
        return
    }

    val popupMenu = PopupMenu()
    val trayIcon = TrayIcon(Toolkit.getDefaultToolkit().getImage("src/main/resources/logo.png"))
    val systemTray = SystemTray.getSystemTray()

    val exitItem = MenuItem("Exit").apply {
        addActionListener {
            exitProcess(1)
        }
    }

    popupMenu.add(exitItem)

    trayIcon.popupMenu = popupMenu

    try {
        systemTray.add(trayIcon)
    } catch (e: AWTException) {
        println("TrayIcon could not be added.")
    }

}