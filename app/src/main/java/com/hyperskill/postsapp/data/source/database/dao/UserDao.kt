package com.hyperskill.postsapp.data.source.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hyperskill.postsapp.data.source.database.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(posts: List<UserEntity>)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()
}