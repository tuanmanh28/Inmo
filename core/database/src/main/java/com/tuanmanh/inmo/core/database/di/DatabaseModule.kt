package com.tuanmanh.inmo.core.database.di

import android.content.Context
import androidx.room.Room
import com.tuanmanh.inmo.core.database.InmoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Provides
    @Singleton
    fun providesInmoDatabase(
        @ApplicationContext context: Context
    ): InmoDatabase = Room.databaseBuilder(
        context,
        InmoDatabase::class.java,
        "inmo-database"
    ).build()
}