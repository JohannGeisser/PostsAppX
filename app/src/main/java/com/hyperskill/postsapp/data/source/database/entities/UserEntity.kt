package com.hyperskill.postsapp.data.source.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hyperskill.postsapp.domain.item.UserItem

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "website") val website: String
)

fun UserItem.toDatabase() = UserEntity(id = id, name = name, email = email, phone = phone, website = website)