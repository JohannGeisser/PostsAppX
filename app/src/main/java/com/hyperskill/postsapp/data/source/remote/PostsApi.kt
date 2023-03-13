package com.hyperskill.postsapp.data.source.remote

import com.hyperskill.postsapp.data.source.remote.model.PostModel
import com.hyperskill.postsapp.utils.Constants.Companion.POSTS_ENDPOINT
import com.hyperskill.postsapp.utils.Constants.Companion.POST_ID_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface PostsApi {

    @GET(POSTS_ENDPOINT)
    suspend fun getPosts(): Response<List<PostModel>>

    @GET(POST_ID_ENDPOINT)
    suspend fun getPostById(@Path("id") postId:Int): Response<PostModel>
}