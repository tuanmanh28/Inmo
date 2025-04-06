package com.tuanmanh.inmo.core.data.di

import com.tuanmanh.inmo.core.data.repository.HabitsRepository
import com.tuanmanh.inmo.core.data.repository.HabitsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsHabitRepository(
        habitsRepository: HabitsRepositoryImpl
    ): HabitsRepository
}