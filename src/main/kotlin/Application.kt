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
import io.ktor.http.HttpStatusCode
import com.finance.model.CreateAccountRequest
import io.ktor.server.request.receive
import io.ktor.server.routing.post


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

            get("/accounts/{id}"){
                val id = call.parameters["id"]?.toIntOrNull()

                if(id == null){
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "Invalid account ID"
                    )
                    return@get
                }

                val account = accountService.getAccountById(id)
                if (account == null){
                    call.respond(
                        HttpStatusCode.NotFound,
                        "Account not found"
                    )
                    return@get
                }
                call.respond(account)
            }

            post("/accounts"){
                val request = call.receive<CreateAccountRequest>()
                val account = accountService.createAccount(request)
                call.respond(HttpStatusCode.Created, account)
            }
        }
    }.start(wait = true)
}