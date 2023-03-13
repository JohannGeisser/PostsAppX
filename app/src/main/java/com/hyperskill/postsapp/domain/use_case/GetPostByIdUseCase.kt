package com.hyperskill.postsapp.domain.use_case

import com.hyperskill.postsapp.data.repositories.PostRepository
import com.hyperskill.postsapp.domain.item.PostItem
import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(private val postRepository: PostRepository) {

    suspend operator fun invoke(id: Int): PostItem {
        return postRepository.getPostByIdFromApi(id)
    }
}