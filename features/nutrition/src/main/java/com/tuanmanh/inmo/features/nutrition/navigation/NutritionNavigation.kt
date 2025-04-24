package com.tuanmanh.inmo.features.nutrition.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.tuanmanh.inmo.features.nutrition.NutritionRoute

@Serializable
data object NutritionRoute


fun NavController.navigateToNutrition(
    navOptions: NavOptions,
) {
    navigate(route = NutritionRoute, navOptions)
}

fun NavGraphBuilder.nutritionScreen(){
    composable<NutritionRoute> {
        NutritionRoute()
    }
}
