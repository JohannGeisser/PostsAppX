package com.hyperskill.postsapp.data.source.remote

import com.hyperskill.postsapp.data.source.remote.model.CommentModel
import com.hyperskill.postsapp.data.source.remote.model.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommentService @Inject constructor(private val commentsApi: CommentsApi) {

    suspend fun getCommentsByPostId(postId: Int): List<CommentModel> {
        return withContext(Dispatchers.IO) {
            val comments = commentsApi.getCommentsByPostId(postId)
            comments.body()!!
        }
    }
}