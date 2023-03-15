package com.github.willor777.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.configureSecurity() {
    
    authentication {
            jwt {
                val jwtAudience = this@configureSecurity.environment.config.property("jwt.audience").getString()
                realm = this@configureSecurity.environment.config.property("jwt.realm").getString()
                val secret = this@configureSecurity.environment.config.property("jwt.secret").getString()
                val issuer = this@configureSecurity.environment.config.property("jwt.issuer").getString()
                verifier(
                    JWT
                        .require(Algorithm.HMAC256(secret))
                        .withAudience(jwtAudience)
                        .withIssuer(issuer)
                        .build()
                )
                validate { credential ->
                    if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
                }
                challenge { defaultScheme, realm ->
                    call.respondText("Invalid Login")
                }
            }
        }
}
