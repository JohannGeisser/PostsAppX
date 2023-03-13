package com.hyperskill.postsapp.data.source.remote.model

data class CommentModel(

    val id: Int,
    val name: String,
    val body: String,
    val email: String,
    val postId: Int
)