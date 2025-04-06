package com.tuanmanh.inmo.core.database.di

import android.content.Context
import androidx.room.Room
import com.tuanmanh.inmo.core.database.InmoDatabase
import com.tuanmanh.inmo.core.database.dao.HabitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class DaosModule {
    @Provides
    fun providesHabitDao(
        database: InmoDatabase,
    ): HabitDao = database.habitDao()
}