package com.tuanmanh.inmo.features.habittracker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tuanmanh.inmo.core.designsystem.component.CustomDatePickerDialog
import com.tuanmanh.inmo.core.designsystem.component.WeekDayLazyRow
import com.tuanmanh.inmo.core.designsystem.theme.InmoTheme
import com.tuanmanh.inmo.core.model.Habit
import com.tuanmanh.inmo.core.model.HabitStatus
import com.tuanmanh.inmo.features.habittracker.components.HabitItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HabitTrackerRoute(
    modifier: Modifier = Modifier,
    navigateToUpdateScreen: (Long?) -> Unit = {},
    viewModel: HabitTrackerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentDate by viewModel.currentDate.collectAsState()
    val selectedDate by viewModel.selectedDate.collectAsState()
    HabitTrackerScreen(
        modifier = modifier,
        uiState = uiState,
        currentDate = currentDate,
        selectedDate = selectedDate,
        onDateSelected = viewModel::onDateSelected,
        toggleHabit = viewModel::toggleHabit,
        deleteHabit = viewModel::deleteHabit,
        skipHabit = viewModel::skipHabit,
        navigateToUpdateScreen = navigateToUpdateScreen
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitTrackerScreen(
    modifier: Modifier = Modifier,
    uiState: HabitTrackerUiState,
    currentDate: LocalDate = LocalDate.now(),
    selectedDate: LocalDate = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit = {},
    toggleHabit: (Long) -> Unit = {},
    skipHabit: (Long) -> Unit = {},
    deleteHabit: (Long) -> Unit = {},
    navigateToUpdateScreen: (Long?) -> Unit = {}
) {
    var showDatePicker by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = if (currentDate == selectedDate) stringResource(R.string.today) else {
                    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")
                    selectedDate.format(dateTimeFormatter)
                }
            )
        }, actions = {
            if (currentDate != selectedDate) {
                IconButton(onClick = { onDateSelected(currentDate) }) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = stringResource(R.string.today)
                    )
                }
            }
            IconButton(onClick = { showDatePicker = true }) {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = stringResource(R.string.view_all_habits)
                )
            }
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = { navigateToUpdateScreen(null) }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_habit)
            )
        }
    }) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            WeekDayLazyRow(
                currentDate = currentDate,
                selectedDate = selectedDate,
                onDateSelected = onDateSelected
            )
            when (uiState) {
                HabitTrackerUiState.Loading -> {
                    LoadingState()
                }

                is HabitTrackerUiState.LoadFailed -> {
                    LoadFailedState(error = uiState.error)
                }

                HabitTrackerUiState.Empty -> {
                    EmptyState()
                }

                is HabitTrackerUiState.Success -> {
                    SuccessState(
                        habits = uiState.habits,
                        toggleHabit = toggleHabit,
                        editHabit = navigateToUpdateScreen,
                        skipHabit = skipHabit,
                    )
                }
            }
        }
        if (showDatePicker) {
            CustomDatePickerDialog(onDateSelected = { date ->
                onDateSelected(date)
                showDatePicker = false
            }, onDismiss = {
                showDatePicker = false
            })
        }

    }
}

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun LoadFailedState(
    modifier: Modifier = Modifier, error: String
) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(
            text = error,
            color = MaterialTheme.colorScheme.error,
        )
    }
}

@Composable
fun EmptyState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.no_habits)
        )
    }
}

@Composable
fun SuccessState(
    modifier: Modifier = Modifier,
    habits: Map<Habit, HabitStatus> = mutableMapOf(),
    toggleHabit: (Long) -> Unit,
    editHabit: (Long?) -> Unit = {},
    skipHabit: (Long) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(habits.keys.toList()) { habit ->
            if (habits[habit] == HabitStatus.SKIPPED) return@items
            HabitItem(
                habitStatus = habits[habit] ?: HabitStatus.NOT_COMPLETED,
                habit = habit,
                onToggle = { toggleHabit(habit.id) },
                onSkip = { skipHabit(habit.id) },
                onEdit = { editHabit(habit.id) },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun HabitTrackerScreenPreview() {
    InmoTheme {
        HabitTrackerScreen(
            uiState = HabitTrackerUiState.Empty
        )
    }
}
 