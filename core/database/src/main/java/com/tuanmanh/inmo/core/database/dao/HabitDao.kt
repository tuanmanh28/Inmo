package com.tuanmanh.inmo.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tuanmanh.inmo.core.database.model.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habitEntity: HabitEntity)

    @Delete
    suspend fun deleteHabit(habitEntity: HabitEntity)

    @Query(
        value = """
            SELECT * FROM habits
            WHERE id = :habitId
        """
    )
    fun getHabitEntity(habitId: String): Flow<HabitEntity>

    @Query(value = "SELECT * FROM habits")
    fun getHabitEntities() : Flow<List<HabitEntity>>

    @Query("UPDATE habits SET isCompletedToday = NOT isCompletedToday WHERE id = :habitId")
    suspend fun toggleHabit(habitId: String)
}