package com.tuanmanh.inmo.features.dashboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.tuanmanh.inmo.features.dashboard.DashboardRoute

@Serializable
data object DashboardRoute 


fun NavController.navigateToDashboard(
    navOptions: NavOptions,
) {
    navigate(route = DashboardRoute, navOptions)
}

fun NavGraphBuilder.dashboardScreen(){
    composable<DashboardRoute> {
        DashboardRoute()
    }
}
