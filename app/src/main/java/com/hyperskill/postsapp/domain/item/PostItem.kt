package com.hyperskill.postsapp.domain.item

import com.hyperskill.postsapp.data.source.database.entities.PostEntity
import com.hyperskill.postsapp.data.source.remote.model.PostModel

data class PostItem(

    val id: Int,
    val body: String,
    val title: String,
    val userId: Int,
    var isFavorite: Boolean
)

fun PostModel.toPostItem() = PostItem(id = id, title = title, body = body, userId = userId, isFavorite = false)
fun PostEntity.toPostItem() = PostItem(id = id, title = title, body = body, userId = userId, isFavorite = isFavorite)