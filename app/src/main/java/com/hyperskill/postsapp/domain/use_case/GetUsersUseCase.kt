package com.hyperskill.postsapp.domain.use_case

import com.hyperskill.postsapp.data.repositories.UserRepository
import com.hyperskill.postsapp.data.source.database.entities.toDatabase
import com.hyperskill.postsapp.domain.item.UserItem
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): List<UserItem> {
        val users = userRepository.getUsersFromApi()

        return if (users.isNotEmpty()) {
            userRepository.clearUsers()
            userRepository.insertUsers(users.map { it.toDatabase() })
            users
        } else {
            userRepository.getUsersFromDatabase()
        }
    }
}