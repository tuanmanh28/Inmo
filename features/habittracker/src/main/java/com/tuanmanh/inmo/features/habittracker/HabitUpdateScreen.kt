package com.tuanmanh.inmo.features.habittracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tuanmanh.inmo.core.designsystem.component.CustomDatePickerDialog
import com.tuanmanh.inmo.core.designsystem.component.CustomTimePickerDialog
import com.tuanmanh.inmo.core.designsystem.theme.InmoTheme
import com.tuanmanh.inmo.core.model.Habit
import com.tuanmanh.inmo.core.model.HabitFrequency
import com.tuanmanh.inmo.core.model.HabitType
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun HabitUpdateRoute(
    modifier: Modifier = Modifier,
    habitId: Long? = null,
    navigateToHabitTracker: () -> Unit = {},
    viewModel: HabitUpdateViewModel = hiltViewModel()
) {
    val habit by viewModel.habit.collectAsState()
    HabitUpdateScreen(
        modifier = modifier,
        habit = habit,
        onUpdate = viewModel::updateHabit,
        onInsert = viewModel::insertHabit,
        onDelete = viewModel::deleteHabit,
        onExit = navigateToHabitTracker
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitUpdateScreen(
    modifier: Modifier = Modifier,
    habit: Habit? = null,
    onUpdate: (Habit) -> Unit = {},
    onInsert: (Habit) -> Unit = {},
    onDelete: (Long) -> Unit = {},
    onExit: () -> Unit = {}
) {
    val habitTypes = HabitType.entries.toList()
    val frequencies = HabitFrequency.entries.toList()
    var name by remember { mutableStateOf(habit?.name.orEmpty()) }
    var description by remember { mutableStateOf(habit?.description.orEmpty()) }
    var selectedType by remember { mutableStateOf(habit?.type ?: HabitType.OTHER) }
    var selectedFrequency by remember {
        mutableStateOf(
            habit?.habitFrequency ?: HabitFrequency.DAILY
        )
    }
    var startDate by remember { mutableStateOf(habit?.startDate ?: LocalDate.now()) }
    var endDate by remember { mutableStateOf(habit?.endDate ?: LocalDate.now()) }
    var reminderTime by remember { mutableStateOf(habit?.reminderTime ?: LocalTime.of(6,30)) }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = if (habit == null) "Create Habit" else "Update Habit") },
            navigationIcon = {
                IconButton(onClick = onExit) {
                    Icon(Icons.Filled.Close, contentDescription = null)
                }
            })
    }, bottomBar = {
        BottomAppBar {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                habit?.let {
                    TextButton(onClick = {
                        onUpdate(
                            Habit(
                                name = name,
                                description = description,
                                type = selectedType,
                                habitFrequency = selectedFrequency,
                                startDate = startDate,
                                endDate = endDate,
                                reminderTime = reminderTime
                            )
                        )
                        onExit()
                    }) {
                        Text("Save")
                    }
                    TextButton(onClick = {
                        onDelete(it.id)
                        onExit()
                    }) {
                        Text("Delete")
                    }
                } ?: run {
                    TextButton(onClick = {
                        onInsert(
                            Habit(
                                name = name,
                                description = description,
                                type = selectedType,
                                habitFrequency = selectedFrequency,
                                startDate = startDate,
                                endDate = endDate,
                                reminderTime = reminderTime
                            )
                        )
                        onExit()
                    }) {
                        Text("Add")
                    }
                }
            }
        }
    }) { paddingValues ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Name Input
            TextField(value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            // Description Input
            TextField(value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            // Habit Type Selector
            DropdownMenuSelector(label = "Select Habit Type",
                items = habitTypes,
                selectedItem = selectedType,
                onItemSelected = { selectedType = it })

            // Frequency Selector
            DropdownMenuSelector(label = "Select Frequency",
                items = frequencies,
                selectedItem = selectedFrequency,
                onItemSelected = { selectedFrequency = it })

            // Start Time Picker
            DatePickerField(label = "Start Date",
                selectedDate = startDate,
                onDateSelected = { startDate = it })

            // End Time Picker
            DatePickerField(label = "End Date",
                selectedDate = endDate,
                onDateSelected = { endDate = it })

            // Reminder Time Picker
            TimePickerField(label = "Reminder Time",
                selectedTime = reminderTime,
                onTimeSelected = { reminderTime = it })
        }
    }
}

@Composable
fun <T> DropdownMenuSelector(
    label: String, items: List<T>, selectedItem: T?, onItemSelected: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selectedItem?.toString() ?: label)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    onItemSelected(item)
                    expanded = false
                }, text = {
                    Text(item.toString())
                })
            }
        }
    }
}

@Composable
fun TimePickerField(
    label: String, selectedTime: LocalTime?, onTimeSelected: (LocalTime) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    OutlinedButton(onClick = { showDialog = true }) {
        Text(selectedTime?.toString() ?: label)
    }
    if (showDialog) {
        CustomTimePickerDialog(onDismiss = { showDialog = false },
            time = selectedTime ?: LocalTime.now(),
            onTimeSelected = {
                onTimeSelected(it)
                showDialog = false
            })
    }
}

@Composable
fun DatePickerField(
    label: String, selectedDate: LocalDate?, onDateSelected: (LocalDate) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    OutlinedButton(onClick = { showDialog = true }) {
        Text(selectedDate?.toString() ?: label)
    }
    if (showDialog) {
        CustomDatePickerDialog(onDismiss = { showDialog = false },
            date = selectedDate ?: LocalDate.now(),
            onDateSelected = {
                onDateSelected(it)
                showDialog = false
            })
    }
}

@Preview
@Composable
private fun HabitUpdateScreenPreview() {
    InmoTheme {
        HabitUpdateScreen()
    }
}