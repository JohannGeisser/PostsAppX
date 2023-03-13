package com.hyperskill.postsapp.data.source.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hyperskill.postsapp.data.source.database.entities.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM post_table ORDER BY isFavorite DESC")
    suspend fun getAllPosts(): List<PostEntity>

    @Query("SELECT * FROM post_table WHERE id = :id")
    suspend fun getPostById(id: Int): PostEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(posts: List<PostEntity>)

    @Query("DELETE FROM post_table")
    suspend fun deleteAllPosts()

    @Query("DELETE FROM post_table WHERE id = :id")
    suspend fun deletePostById(id: Int)

    @Query("UPDATE post_table SET isFavorite = :favoriteState WHERE id = :postId")
    suspend fun updatePostIsFavorite(postId: Int, favoriteState: Boolean)
}