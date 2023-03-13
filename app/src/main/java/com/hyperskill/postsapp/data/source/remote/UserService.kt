package com.hyperskill.postsapp.data.source.remote

import com.hyperskill.postsapp.data.source.remote.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(private val usersApi: UsersApi) {

    suspend fun getUsers(): List<UserModel> {
        return withContext(Dispatchers.IO) {
            val users = usersApi.getUsers()
            users.body() ?: emptyList()
        }
    }

    suspend fun getUserById(id: Int): UserModel {
        return withContext(Dispatchers.IO) {
            val user = usersApi.getUserById(id)
            user.body()!!
        }
    }
}
