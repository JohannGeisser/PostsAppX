package com.hyperskill.postsapp.data.source.remote

import com.hyperskill.postsapp.data.source.remote.model.CommentModel
import com.hyperskill.postsapp.data.source.remote.model.PostModel
import com.hyperskill.postsapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentsApi {

    @GET(Constants.COMMENTS_ENDPOINT)
    suspend fun getCommentsByPostId(@Path("id") id: Int): Response<List<CommentModel>>
}