package com.github.willor777

import com.github.willor777.plugins.configureRouting
import com.github.willor777.plugins.configureSecurity
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>) {


    // This is how to use config file with embeddedServer: application.conf or .yaml
    // For EngineMain (real server) it's different
    embeddedServer(Netty, commandLineEnvironment(args))
        .start(wait = true)


}

fun Application.module() {

    // Set up JWT security
    configureSecurity()

    // Set up routes
    configureRouting()

    install(ContentNegotiation){
        json()
    }
}
