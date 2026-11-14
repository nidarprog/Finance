package com.finance

import com.finance.model.Account
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.response.respond
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json


fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "127.0.0.1"
    ){
        install(ContentNegotiation) {
            json()
        }

        routing {
            get("/health"){
                call.respondText { "Finance API is running! " }
            }

            get("/accounts"){
                val accounts = listOf(
                    Account(1, "Checking", 1000.54),
                    Account(2,"Saving", 350.0)
                )
                call.respond(accounts)
            }
        }
    }.start(wait = true)
}