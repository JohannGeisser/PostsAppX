package com.hyperskill.postsapp.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hyperskill.postsapp.data.source.database.dao.CommentDao
import com.hyperskill.postsapp.data.source.database.dao.PostDao
import com.hyperskill.postsapp.data.source.database.dao.UserDao
import com.hyperskill.postsapp.data.source.database.entities.CommentEntity
import com.hyperskill.postsapp.data.source.database.entities.PostEntity
import com.hyperskill.postsapp.data.source.database.entities.UserEntity

@Database(entities = [PostEntity::class, UserEntity::class, CommentEntity::class], version = 1)
abstract class PostDatabase : RoomDatabase() {

    abstract fun getPostDao(): PostDao
    abstract fun getUserDao(): UserDao
    abstract fun getCommentDao(): CommentDao
}