package com.tuanmanh.inmo.core.data.repository.impl

import com.tuanmanh.inmo.core.data.repository.TimeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

class TimeRepositoryImpl @Inject constructor() : TimeRepository {
    private val _currentDate = MutableStateFlow(LocalDate.now())
    override val currentDate: Flow<LocalDate>
        get() = _currentDate.asStateFlow()
    private val _selectedDate = MutableStateFlow(LocalDate.now())
    override val selectedDate: Flow<LocalDate>
        get() = _selectedDate.asStateFlow()

    init {
        CoroutineScope(Dispatchers.Default).launch {
            getCurrentDate()
        }
    }

    private suspend fun getCurrentDate() {
        while (true) {
            val now = LocalDate.now()
            val nextMidnight =
                now.plusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()
            val nowInstant = Instant.now()
            val delayMillis = Duration.between(nowInstant, nextMidnight).toMillis()

            delay(delayMillis)
            _currentDate.value = LocalDate.now()
        }
    }

    override fun onDateSelected(date: LocalDate) {
        _selectedDate.value = date
    }
}