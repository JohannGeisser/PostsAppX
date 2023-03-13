package com.hyperskill.postsapp.data.source.remote

import com.hyperskill.postsapp.data.source.remote.model.UserModel
import com.hyperskill.postsapp.utils.Constants.Companion.USERS_ENDPOINT
import com.hyperskill.postsapp.utils.Constants.Companion.USER_ID_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {

    @GET(USERS_ENDPOINT)
    suspend fun getUsers(): Response<List<UserModel>>

    @GET(USER_ID_ENDPOINT)
    suspend fun getUserById(@Path("id") id:Int): Response<UserModel>
}