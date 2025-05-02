package com.tuanmanh.inmo.core.designsystem.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tuanmanh.inmo.core.designsystem.theme.InmoTheme
import java.time.LocalDate

@Composable
fun WeekDayLazyRow(
    modifier: Modifier = Modifier,
    currentDate: LocalDate = LocalDate.now(),
    selectedDate: LocalDate = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit = {}
) {
    val dayList = List(20001) {
        currentDate.minusDays(1000L).plusDays(it.toLong())
    }
    val defaultOffset = currentWindowSize().width / 2 - 55
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = dayList.indexOf(currentDate),
        initialFirstVisibleItemScrollOffset = -defaultOffset
    )
    LaunchedEffect(selectedDate) {
        val visibleItemsInfo = listState.layoutInfo.visibleItemsInfo
        val selectedDateIndex = dayList.indexOf(selectedDate)
        if (!visibleItemsInfo.map { it.index }.contains(selectedDateIndex)) {
            listState.scrollToItem(selectedDateIndex, -defaultOffset)
        }
    }
    LazyRow(
        modifier = modifier, state = listState
    ) {
        items(dayList.size) { index ->
            WeekDayChip(date = dayList[index],
                isCurrentDate = dayList[index] == currentDate,
                isSelected = dayList[index] == selectedDate,
                onDateSelected = { onDateSelected(dayList[index]) })
        }
    }
}

@Preview(backgroundColor = 0x00000000, showBackground = true)
@Composable
private fun DayLazyRowPreview() {
    InmoTheme {
        WeekDayLazyRow()
    }
}