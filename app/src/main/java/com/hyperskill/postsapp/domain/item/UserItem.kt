package com.hyperskill.postsapp.domain.item

import com.hyperskill.postsapp.data.source.database.entities.UserEntity
import com.hyperskill.postsapp.data.source.remote.model.UserModel

data class UserItem(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val website: String
)

fun UserModel.toUserItem() = UserItem(id = id, name = name, email = email, phone = phone, website = website)
fun UserEntity.toUserItem() = UserItem(id = id, name = name, email = email, phone = phone, website = website)

