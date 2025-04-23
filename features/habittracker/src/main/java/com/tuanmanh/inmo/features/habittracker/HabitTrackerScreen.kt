package com.tuanmanh.inmo.features.habittracker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
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
import com.tuanmanh.inmo.core.designsystem.theme.InmoTheme
import com.tuanmanh.inmo.features.habittracker.components.AddHabitDialog
import com.tuanmanh.inmo.features.habittracker.components.HabitItem

@Composable
fun HabitTrackerRoute(
    modifier: Modifier = Modifier,
    onNavigateToHabitList: () -> Unit = {},
    viewModel: HabitTrackerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    HabitTrackerScreen(
        modifier = modifier,
        uiState = uiState,
        onNavigateToHabitList = onNavigateToHabitList,
        toggleHabit = viewModel::toggleHabit,
        deleteHabit = viewModel::deleteHabit,
        addHabit = viewModel::addHabit
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitTrackerScreen(
    modifier: Modifier = Modifier,
    uiState: HabitTrackerUiState = HabitTrackerUiState.Empty,
    onNavigateToHabitList: () -> Unit = {},
    toggleHabit: (Long) -> Unit = {},
    deleteHabit: (Long) -> Unit = {},
    addHabit: (String) -> Unit = {},
) {
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(R.string.habit_tracker)) }, actions = {
            IconButton(onClick = onNavigateToHabitList) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = stringResource(R.string.view_all_habits)
                )
            }
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = { showAddDialog = true }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_habit)
            )
        }
    }) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                HabitTrackerUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is HabitTrackerUiState.LoadFailed -> {
                    Text(
                        text = uiState.error,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
                HabitTrackerUiState.Empty -> {
                    Text(
                        text = stringResource(R.string.no_habits),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }
                is HabitTrackerUiState.Success -> {
                    LazyColumn {
                        items(uiState.habits) { habit ->
                            HabitItem(habit = habit,
                                onToggle = { toggleHabit(habit.id) },
                                onDelete = { deleteHabit(habit.id) })
                        }
                    }
                }
            }
        }

        if (showAddDialog) {
            AddHabitDialog(onDismiss = { showAddDialog = false }, onConfirm = { name ->
                addHabit(name)
                showAddDialog = false
            })
        }

    }
}

@Preview
@Composable
private fun HabitTrackerScreenPreview() {
    InmoTheme {
        HabitTrackerScreen()
    }
}
