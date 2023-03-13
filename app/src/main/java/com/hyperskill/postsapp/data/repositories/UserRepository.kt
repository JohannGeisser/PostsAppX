package com.hyperskill.postsapp.data.repositories

import com.hyperskill.postsapp.data.source.database.dao.UserDao
import com.hyperskill.postsapp.data.source.database.entities.UserEntity
import com.hyperskill.postsapp.data.source.remote.UserService
import com.hyperskill.postsapp.domain.item.UserItem
import com.hyperskill.postsapp.domain.item.toUserItem
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService,
    private val userDao: UserDao
) {

    suspend fun getUsersFromApi(): List<UserItem> {
        return userService.getUsers().map {
            it.toUserItem()
        }
    }

    suspend fun getUserByIdFromApi(id: Int): UserItem {
        return userService.getUserById(id).toUserItem()
    }

    suspend fun getUsersFromDatabase(): List<UserItem> {
        val response: List<UserEntity> = userDao.getAllUsers()
        return response.map { it.toUserItem() }
    }

    suspend fun insertUsers(users: List<UserEntity>) {
        userDao.insertAllUsers(users)
    }

    suspend fun clearUsers() {
        userDao.deleteAllUsers()
    }
}