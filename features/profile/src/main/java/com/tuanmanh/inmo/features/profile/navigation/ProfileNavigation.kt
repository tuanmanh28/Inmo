package com.tuanmanh.inmo.features.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.tuanmanh.inmo.features.profile.ProfileRoute

@Serializable
data object ProfileRoute


fun NavController.navigateToProfile(
    navOptions: NavOptions,
) {
    navigate(route = ProfileRoute, navOptions)
}

fun NavGraphBuilder.profileScreen(){
    composable<ProfileRoute> {
        ProfileRoute()
    }
}
