package com.hyperskill.postsapp.data.source.remote

import com.hyperskill.postsapp.data.source.remote.model.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostService @Inject constructor(
    private val postsApi: PostsApi
) {

    suspend fun getPosts(): List<PostModel> {
        return withContext(Dispatchers.IO) {
            val posts = postsApi.getPosts()
            posts.body() ?: emptyList()
        }
    }

    suspend fun getPostById(id: Int): PostModel {
        return withContext(Dispatchers.IO) {
            val post = postsApi.getPostById(id)
            post.body()!!
        }
    }
}