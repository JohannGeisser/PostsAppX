package com.hyperskill.postsapp.di

import android.content.Context
import androidx.room.Room
import com.hyperskill.postsapp.data.source.database.PostDatabase
import com.hyperskill.postsapp.utils.Constants.Companion.POST_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PostDatabase::class.java, POST_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providePostDao(db: PostDatabase) = db.getPostDao()

    @Singleton
    @Provides
    fun provideUserDao(db: PostDatabase) = db.getUserDao()

}