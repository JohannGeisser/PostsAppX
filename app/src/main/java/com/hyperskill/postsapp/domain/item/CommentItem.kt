package com.hyperskill.postsapp.domain.item

data class CommentItem(

    val id: Int,
    val name: String,
    val body: String,
    val email: String,
    val postId: Int
)
