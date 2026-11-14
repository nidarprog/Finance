package com.finance

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
import io.ktor.server.response.respondText
import io.ktor.server.routing.get


fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "127.0.0.1"
    ){
        routing {
            get("/health"){
                call.respondText { "Finance API is running! " }
            }
        }
    }.start(wait = true)
}