package com.hyperskill.postsapp.domain.use_case

import com.hyperskill.postsapp.data.repositories.PostRepository
import javax.inject.Inject

class DeleteAllPostsUseCase @Inject constructor(private val postRepository: PostRepository) {

    suspend operator fun invoke() {
        postRepository.clearPosts()
    }
}