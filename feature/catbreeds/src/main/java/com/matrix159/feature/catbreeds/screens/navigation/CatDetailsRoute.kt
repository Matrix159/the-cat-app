package com.matrix159.feature.catbreeds.screens.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.matrix159.feature.catbreeds.screens.catdetails.CatDetailsScreen

fun NavGraphBuilder.catDetailsRoute(
  showTwoPane: Boolean = false,
  navController: NavController
) {
  composable(
    CatBreedNavigationRoutes.CatDetailsScreen.route,
    arguments = listOf(navArgument(CatBreedNavigationRoutes.CatDetailsScreen.BREED_ID) {
      type = NavType.StringType
    })
  ) {
    CatDetailsScreen(
      showTwoPane = showTwoPane,
      navigateBack = navController::popBackStack,
      modifier = Modifier.fillMaxSize()
    )
  }
}