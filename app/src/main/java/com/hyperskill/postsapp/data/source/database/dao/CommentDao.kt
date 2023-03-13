package com.hyperskill.postsapp.data.source.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.hyperskill.postsapp.data.source.database.entities.CommentEntity

@Dao
interface CommentDao {

    @Query("SELECT * FROM post_table WHERE id = :postId")
    suspend fun getCommentsByPostId(postId: Int): List<CommentEntity>
}