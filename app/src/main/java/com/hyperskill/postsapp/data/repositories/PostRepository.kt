package com.hyperskill.postsapp.data.repositories

import com.hyperskill.postsapp.data.source.database.dao.PostDao
import com.hyperskill.postsapp.data.source.database.entities.PostEntity
import com.hyperskill.postsapp.data.source.remote.PostService
import com.hyperskill.postsapp.domain.item.PostItem
import com.hyperskill.postsapp.domain.item.toPostItem
import kotlinx.coroutines.delay
import javax.inject.Inject


class PostRepository @Inject constructor(
    private val postService: PostService,
    private val postDao: PostDao
) {

    suspend fun getPostsFromApi(): List<PostItem> {
        return postService.getPosts().map {
            it.toPostItem()
        }
    }

    suspend fun getPostByIdFromApi(id: Int): PostItem {
        return postService.getPostById(id).toPostItem()
    }

    suspend fun getPostsFromDatabase(): List<PostItem> {
        val response: List<PostEntity> = postDao.getAllPosts()
        return response.map {
            it.toPostItem()
        }
    }

    suspend fun getPostByIdFromDatabase(id: Int): PostItem {
        val response: PostEntity = postDao.getPostById(id)
        return response.toPostItem()
    }

    suspend fun insertPosts(posts: List<PostEntity>) {
        postDao.insertAllPosts(posts)
    }

    suspend fun clearPosts() {
       postDao.deleteAllPosts()
    }

    suspend fun deletePostById(id: Int) {
        postDao.deletePostById(id)
    }

    suspend fun updateIsFavoritePostByIdFromDatabase(id: Int, favoriteState: Boolean) {
        postDao.updatePostIsFavorite(id, favoriteState)
    }
}