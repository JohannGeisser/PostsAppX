package com.hyperskill.postsapp.data.source.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hyperskill.postsapp.domain.item.PostItem

@Entity(tableName = "post_table")
data class PostEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean = false
)

fun PostItem.toDatabase() = PostEntity(id = id, title = title, body = body, userId = userId)
