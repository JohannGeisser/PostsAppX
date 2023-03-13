package com.hyperskill.postsapp.domain.use_case

import com.hyperskill.postsapp.data.repositories.UserRepository
import com.hyperskill.postsapp.domain.item.UserItem
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(id: Int): UserItem {
        return userRepository.getUserByIdFromApi(id)
    }
}