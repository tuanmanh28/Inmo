package com.tuanmanh.inmo.features.habittracker.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tuanmanh.inmo.core.model.Habit
import com.tuanmanh.inmo.features.habittracker.R
import com.tuanmanh.inmo.features.habittracker.components.DaySelector
import com.tuanmanh.inmo.features.habittracker.components.DeleteHabitDialog
import com.tuanmanh.inmo.features.habittracker.components.EmptyHabitsState
import com.tuanmanh.inmo.features.habittracker.components.HabitItem
import com.tuanmanh.inmo.features.habittracker.viewmodel.HabitListViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitListScreen(
    viewModel: HabitListViewModel = hiltViewModel(),
    onNavigateToHabitTracker: () -> Unit = {}
) {
    val habits by viewModel.habits.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    var habitToDelete by remember { mutableStateOf<Habit?>(null) }
    var selectedDay by remember { mutableStateOf(LocalDate.now().dayOfWeek) }

    LaunchedEffect(Unit) {
        viewModel.loadHabits()
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(R.string.habit_tracker)) },
            navigationIcon = {
                IconButton(onClick = onNavigateToHabitTracker) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        )
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            DaySelector(
                selectedDay = selectedDay,
                onDaySelected = { selectedDay = it },
                modifier = Modifier.padding(vertical = 8.dp)
            )

            if (habits.isEmpty()) {
                EmptyHabitsState()
            } else {
                LazyColumn {
                    items(habits) { habit ->
                        HabitItem(habit = habit,
                            onToggle = { viewModel.toggleHabit(habit.id) },
                            onDelete = {
                                habitToDelete = habit
                                showDeleteDialog = true
                            })
                    }
                }
            }
        }
    }

    if (showDeleteDialog && habitToDelete != null) {
        DeleteHabitDialog(habitName = habitToDelete!!.name, onDismiss = {
            showDeleteDialog = false
            habitToDelete = null
        }, onConfirm = {
            viewModel.deleteHabit(habitToDelete!!.id)
            showDeleteDialog = false
            habitToDelete = null
        })
    }
} 