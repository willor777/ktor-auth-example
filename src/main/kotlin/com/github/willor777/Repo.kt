package com.github.willor777

import com.github.willor777.models.User
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*

object Repo {

    // Class which handles generating token
    val tokenManager = TokenManager(HoconApplicationConfig(ConfigFactory.load()))

    // Temp storage for users
    val usersDatabase: MutableMap<String, User> = mutableMapOf()

    // Register New User
    fun registerUser(username: String, password: String): Boolean{
        // TODO Confirmation of username and password structure
        usersDatabase[username] = User(username, password)
        return true
    }

    // Login User
    fun loginUser(user: User): String? {
        // TODO Verify User against data base
        return tokenManager.generateJwtToken(user)
    }

}