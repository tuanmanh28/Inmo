package com.tuanmanh.inmo.features.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.tuanmanh.inmo.features.settings.SettingsRoute

@Serializable
data object SettingsRoute


fun NavController.navigateToSettings(
    navOptions: NavOptions,
) {
    navigate(route = SettingsRoute, navOptions)
}

fun NavGraphBuilder.settingsScreen(){
    composable<SettingsRoute> {
        SettingsRoute()
    }
}
