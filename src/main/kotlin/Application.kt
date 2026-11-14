package com.finance

import com.finance.model.Account
import com.finance.service.AccountService
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

    val accountService = AccountService()
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
                call.respond(accountService.getAllAccounts())
            }
        }
    }.start(wait = true)
}