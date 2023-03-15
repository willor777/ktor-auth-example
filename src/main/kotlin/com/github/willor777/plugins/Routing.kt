package com.github.willor777.plugins

import com.github.willor777.basicRoutes
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {

    routing {
        basicRoutes()
    }

}
