package com.hyperskill.postsapp.domain.use_case

import com.hyperskill.postsapp.data.repositories.PostRepository
import com.hyperskill.postsapp.data.source.database.entities.toDatabase
import com.hyperskill.postsapp.domain.item.PostItem
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postRepository: PostRepository) {

    suspend operator fun invoke(): List<PostItem> {

        var posts = postRepository.getPostsFromDatabase()
        return if (posts.isEmpty()) {
            posts = postRepository.getPostsFromApi()
            postRepository.insertPosts(posts.map { it.toDatabase() })
            posts
        } else {
            postRepository.getPostsFromDatabase()
        }

//        val posts = postRepository.getPostsFromApi()
//
//        return if (posts.isNotEmpty()) {
//            postRepository.clearPosts()
//            postRepository.insertPosts(posts.map {
//                it.toDatabase()
//            })
//            posts
//        } else {
//            postRepository.getPostsFromDatabase()
//        }
    }
}