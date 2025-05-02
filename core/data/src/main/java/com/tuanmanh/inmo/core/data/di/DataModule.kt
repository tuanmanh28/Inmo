package com.tuanmanh.inmo.core.data.di

import com.tuanmanh.inmo.core.data.repository.HabitRecordRepository
import com.tuanmanh.inmo.core.data.repository.HabitsRepository
import com.tuanmanh.inmo.core.data.repository.impl.HabitsRepositoryImpl
import com.tuanmanh.inmo.core.data.repository.TimeRepository
import com.tuanmanh.inmo.core.data.repository.impl.HabitRecordRepositoryImpl
import com.tuanmanh.inmo.core.data.repository.impl.TimeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsTimeRepository(
        timeRepository: TimeRepositoryImpl
    ): TimeRepository

    @Binds
    internal abstract fun bindsHabitRepository(
        habitsRepository: HabitsRepositoryImpl
    ): HabitsRepository

    @Binds
    internal abstract fun bindsHabitRecordRepository(
        habitRecordRepository: HabitRecordRepositoryImpl
    ): HabitRecordRepository
}