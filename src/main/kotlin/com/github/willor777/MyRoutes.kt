package com.github.willor777

import com.github.willor777.models.LoginRespDTO
import com.github.willor777.models.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.basicRoutes() {

    route("/api"){


        /* This route registers the user.
        * Meaning the user will select their username and password.
        * Send it to this route.
        * The server will check creds (doesn't already exist + long enough password + etc...)
        * The server will save the creds into the User Profiles Database
        * The server should respond with some form of confirmation / failure text
        *  */
        post("/register"){

            // Pull registration data sent by user
            val username = call.parameters.get("username") ?: call.respondText("No Username")
            val password = call.parameters.get("password") ?: call.respondText("No Password")

            // Register user in database
            val registrationSuccess = Repo.registerUser(username.toString(), password.toString())

            // Check success
            if (registrationSuccess){
                call.respondText {
                    "Thanks, You are registered"
                }
            }
        }


        /* This route actually logs in the user, and creates a JWT token
        * User sends username + password
        * Server checks UserProfile Database to see if credentials exist
        * If they do, Server will create JwtToken and return to user
        * */
        get("/login"){

            val username = call.parameters.get("username") ?: call.respondText("No Username")
            val password = call.parameters.get("password") ?: call.respondText("No Password")
            val jwtToken = Repo.loginUser(User(username.toString(), password.toString()))

            val response = LoginRespDTO(jwtToken!!, System.currentTimeMillis() + 600000)

            call.respond(HttpStatusCode.Accepted, response)
        }


        /* In order to access this route, the user will need to include a Header with Key and Value in format...
        * Key: "Authorization"
        * Value: "Bearer $JwtTokenHere"
        *
        * JwtToken is recieved from the GET /login route above
        * */
        authenticate {
            get("/protected"){
                call.respondText(text = "Access to protected route is a success!!")
            }
        }
    }
}