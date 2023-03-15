package com.github.willor777.domain

import com.github.willor777.models.User

interface UserTableDao {

    suspend fun insertUser(user: User)

    suspend fun getUser(id: Int): User

    suspend fun getAllUsers(): List<User>

    suspend fun editUser(id: Int, replacement: User)

    suspend fun deleteUser(id: Int)


}