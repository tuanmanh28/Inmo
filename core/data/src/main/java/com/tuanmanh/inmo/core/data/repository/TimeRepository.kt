package com.tuanmanh.inmo.core.data.repository

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TimeRepository {
    val currentDate: Flow<LocalDate>
    val selectedDate: Flow<LocalDate>
    fun onDateSelected(date: LocalDate)
}