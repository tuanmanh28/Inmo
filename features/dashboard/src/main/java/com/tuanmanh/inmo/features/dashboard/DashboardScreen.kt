package com.tuanmanh.inmo.features.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tuanmanh.inmo.core.designsystem.theme.InmoTheme

@Composable
fun DashboardRoute(
    modifier: Modifier = Modifier,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {
    DashboardScreen(
        modifier = modifier
    )
}

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Dashboard",
            fontSize = 50.sp
        )
    }

}

@Preview
@Composable
private fun DashboardScreenPreview() {
    InmoTheme {
        DashboardScreen()
    }
}