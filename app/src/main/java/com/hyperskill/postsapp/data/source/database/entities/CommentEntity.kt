package com.hyperskill.postsapp.data.source.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment_table")
data class CommentEntity(

    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "postId") val postId: Int
)
