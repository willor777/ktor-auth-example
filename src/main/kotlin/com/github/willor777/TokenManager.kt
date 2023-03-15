package com.github.willor777

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.willor777.models.User
import io.ktor.server.config.*


/**
 * Responsible for generating JWT tokens
 */
class TokenManager(
    private val config: HoconApplicationConfig
    ) {


    fun generateJwtToken(user: User): String {
        val audience = config.property("jwt.audience").getString()
        val issuer = config.property("jwt.issuer").getString()
        val secret = config.property("jwt.secret").getString()
        val expiryDate = System.currentTimeMillis() + 6000

        val jwtToken = JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", user.password)
            .sign(Algorithm.HMAC256(secret))
        return jwtToken
    }

}